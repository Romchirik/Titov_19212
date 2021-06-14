package nsu.titov.logic;

import nsu.titov.messages.Handshake;
import nsu.titov.messages.Message;
import nsu.titov.messages.PieceMessage;
import nsu.titov.messages.RequestMessage;
import nsu.titov.metainfo.MetaData;
import nsu.titov.network.MessageReader;
import nsu.titov.peer.PeerInfo;
import nsu.titov.peer.Settings;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Client implements Runnable, MessageHandler {

    private final static long REQUEST_DELAY = 5000;
    long lastRequest = 0;
    private static final Logger logger = Logger.getLogger(Client.class);
    //private part

    private int piecesCount;
    private List<PieceStatus> piecesStatus;
    private int newConnectionId = 1;
    private final PeerInfo peerInfo;
    private MetaData metaData;
//    private final List<SocketChannel> openedConnections = new ArrayList<>();


    FilesystemManager filesystemManager = new FilesystemManager();

    Handshake handshake;

    Selector selector;


    private boolean running = true;

    public Client(PeerInfo peerInfo) throws IOException {
        this.metaData = peerInfo.metaData;
        this.peerInfo = peerInfo;

        handshake = Handshake.createHandshake().setProtocolName(metaData.name);
        selector = Selector.open();
        piecesCount = metaData.pieces.length / 20;
        piecesStatus = new ArrayList<>(piecesCount);
        for (int i = 0; i < piecesCount; i++) {
            piecesStatus.add(PieceStatus.PENDING);
        }
        filesystemManager.setNumPieces(piecesCount);
    }

    private boolean openConnection(SocketChannel channel) {
        try {
            int bytesRead = 0;
            ByteBuffer incomingHandshake = ByteBuffer.allocate(Handshake.TOTAL_SIZE);
            channel.write(ByteBuffer.wrap(handshake.getBytes()));
            while (bytesRead != Handshake.TOTAL_SIZE) {
                bytesRead = channel.read(incomingHandshake);
            }

            if (!handshake.equals(Handshake.createHandshake(incomingHandshake.array()))) {
                logger.warn("Client unable to enter other swarm, connection rejected");
                return false;
            }
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ, new MessageReader(newConnectionId));
            newConnectionId++;
        } catch (IOException e) {
            logger.error("Unable to establish connection with" + channel);
            return false;
        }
        return true;
    }

    private boolean init() {
        for (InetSocketAddress peer : peerInfo.availablePeers) {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.connect(peer);
                if (openConnection(channel)) {
                    continue;
                }
            } catch (IOException ignored) {
            }
            logger.warn("Unable to open connection with" + peer);
        }
        return true;
    }

    @Override
    public void run() {


        init();
        if (newConnectionId == 1) {
            logger.warn("No connections was opened :(");
            return;
        }

        RequestMessage requestMessage = new RequestMessage()
                .setBlockLength(Settings.MAX_MESSAGE_SIZE)
                .setBlockOffset(0)
                .setPieceIndex(0);
        while (running) {

            if (lastRequest + REQUEST_DELAY < System.currentTimeMillis()) {
                updateRequests();
                sendRequests();
                lastRequest = System.currentTimeMillis();
            }


            Set<SelectionKey> selected = null;

            try {
                selector.select();
                selected = selector.selectedKeys();
            } catch (IOException e) {
                break;
            }

            for (SelectionKey key : selected) {
                if (key.isReadable() && 0 < (key.interestOps() & SelectionKey.OP_READ)) {
                    MessageReader reader = (MessageReader) key.attachment();
                    //blocking shit
                    try {
                        Message message = readMessage(key);
                        while (message != null) {
                            message.handle(this, reader, key);
                            message = readMessage(key);
                        }
                    } catch (IOException e) {
                        logger.error("Error occurred while receiving message from " + reader.id + " disconnecting");
                        key.cancel();
                    }


                }
            }

            if(checkFinish()){
                try {
                    filesystemManager.merge(new File("./saved/" + metaData.name));
                } catch (IOException e) {
                    logger.warn("Unable to merge all pieces, may be you trying to overwrite");
                }
            }
        }
    }

    private void sendRequests() {
        logger.info("Resending requests");

        for (int i = 0; i < piecesCount; i++) {
            if (piecesStatus.get(i) == PieceStatus.PENDING) {
                piecesStatus.set(i, PieceStatus.REQUESTED);

                RequestMessage message = new RequestMessage().setBlockLength(Settings.MAX_MESSAGE_SIZE).setBlockOffset(0).setPieceIndex(i);

                Set<SelectionKey> available = selector.keys();
                int skip = i % available.size();
                for (SelectionKey key : available) {
                    if (skip != 0) {
                        skip--;
                        continue;
                    }
                    SocketChannel channel = (SocketChannel) key.channel();
                    MessageReader reader = (MessageReader) key.attachment();

                    try {
                        reader.sendMessage(message, channel);
                        logger.info("Requested piece " + i + " from " + reader.id);
                        break;
                    } catch (IOException e) {
                        logger.error("Connection with " + reader.id + "broken, closing connection");
                        key.cancel();
                    }
                }


            }
        }
    }


    private boolean checkFinish() {
        for (int i = 0; i < piecesCount; i++) {
            if (piecesStatus.get(i) != PieceStatus.VALIDATED) {
                return false;
            }
        }
        return true;
    }

    private void updateRequests() {
        for (int i = 0; i < piecesCount; i++) {
            if (piecesStatus.get(i) != PieceStatus.VALIDATED) {
                piecesStatus.set(i, PieceStatus.PENDING);
            }
        }
    }

    @Override
    public void handle(RequestMessage message, MessageReader reader, SelectionKey key) {

    }

    @Override
    public void handle(PieceMessage message, MessageReader reader, SelectionKey key) {
        logger.info(String.format("Received PIECE message from %d. index: %d, offset: %d", reader.id, message.getPieceIndex(), message.getBlockOffset()));
        SocketChannel channel = (SocketChannel) key.channel();

        if (message.getPieceIndex() > piecesCount - 1) {
            logger.warn("Ignoring PIECE message, invalid piece index");
            return;
        }
        if (!filesystemManager.validatePiece(message.getBlock(), Arrays.copyOfRange(metaData.pieces, message.getPieceIndex() * 20, message.getPieceIndex() * 20 + 20))) {
            logger.warn("Ignoring PIECE message, invalid piece hash");
            return;
        }

        logger.info("PIECE message validated successfully!");
        piecesStatus.set(message.getPieceIndex(), PieceStatus.VALIDATED);
        filesystemManager.addPiece(message.getPieceIndex(), message.getBlock());
    }

    private Message readMessage(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        MessageReader reader = (MessageReader) key.attachment();

        reader.update(channel);
        if (reader.ifIncoming()) {
            return reader.getMessage();
        } else {
            return null;
        }

    }
}

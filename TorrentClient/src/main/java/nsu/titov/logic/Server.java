package nsu.titov.logic;

import nsu.titov.messages.Handshake;
import nsu.titov.messages.Message;
import nsu.titov.messages.PieceMessage;
import nsu.titov.messages.RequestMessage;
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
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Server implements Runnable, MessageHandler {

    private static final Logger logger = Logger.getLogger(Server.class);

    File sharingFile;
    PeerInfo peerInfo;

    Handshake handshake;

    Selector selector;
    ServerSocketChannel server;

    int newClientId = 1;
    boolean running = true;

    FilesystemManager filesystemManager = new FilesystemManager();

    public Server(PeerInfo peerInfo, File sharingFile) throws IOException {
        this.peerInfo = peerInfo;
        this.sharingFile = sharingFile;
        selector = Selector.open();
        server = ServerSocketChannel.open();

        server.bind(new InetSocketAddress(peerInfo.incomingPort));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void init() {
        handshake = Handshake.createHandshake();
        handshake.setProtocolName(peerInfo.metaData.name);
    }

    private boolean acceptClient(SocketChannel channel) {
        try {
            int bytesRead = 0;
            ByteBuffer incomingHandshake = ByteBuffer.allocate(Handshake.TOTAL_SIZE);
            channel.write(ByteBuffer.wrap(handshake.getBytes()));
            while (bytesRead != Handshake.TOTAL_SIZE) {
                bytesRead = channel.read(incomingHandshake);
            }

            if (!handshake.equals(Handshake.createHandshake(incomingHandshake.array()))) {
                logger.warn("Client unable to enter this swarm, connection rejected");
                return false;
            }
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ, new MessageReader(newClientId));
            newClientId++;
        } catch (IOException e) {
            logger.error("Unable to establish connection with" + channel);
            return false;
        }
        return true;
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

    @Override
    public void run() {
        init();

        Set<SelectionKey> selected = null;

        while (running) {
            try {
                selector.select();
                selected = selector.selectedKeys();
            } catch (IOException e) {
                break;
            }

            for (SelectionKey key : selected) {
                if (key.isAcceptable() && 0 < (key.interestOps() & SelectionKey.OP_ACCEPT)) {
                    try {
                        SocketChannel channel = server.accept();
                        if (channel != null) {
                            acceptClient(channel);
                            logger.info("New client accepted! " + (newClientId - 1));
                        }
                    } catch (IOException e) {
                        logger.error("Error occurred while accepting client");
                        continue;
                    }
                }

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
                        logger.error("Error occurred while receiving message from " + reader.id + " disconnecting client");
                        key.cancel();
                    }


                }
            }
        }
    }


    @Override
    public void handle(RequestMessage message, MessageReader reader, SelectionKey key) {
        logger.info("Received REQUEST message from " + reader.id);

        int index = message.getPieceIndex();
        int size = message.getBlockLength();

        byte[] piece = filesystemManager.readPiece(sharingFile, index, size);
        if (piece == null) {
            logger.error("Unable to read piece, ignoring message");
            return;
        }

        PieceMessage response = new PieceMessage()
                .setPieceIndex(index)
                .setBlockOffset(0)
                .setBlock(piece);

        try {
            reader.sendMessage(response, (SocketChannel) key.channel());
        } catch (IOException e) {
            logger.warn("Broken connection with" + reader.id + "closing connection");
            key.cancel();
            return;
        }

    }

    @Override
    public void handle(PieceMessage message, MessageReader reader, SelectionKey key) {

    }
}

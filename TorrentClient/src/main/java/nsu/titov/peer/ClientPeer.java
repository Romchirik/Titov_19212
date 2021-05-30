package nsu.titov.peer;

import nsu.titov.messages.CancelMessage;
import nsu.titov.messages.MessageFactory;
import nsu.titov.messages.MessageId;
import nsu.titov.network_simple.ConnectionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ClientPeer {
    public static void main(String[] args) throws IOException {

        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress(Settings.DEFAULT_PORT));

        ConnectionHandler handler = new ConnectionHandler(socket);

        CancelMessage tmp = (CancelMessage) MessageFactory.createMessage(MessageId.CANCEL);
        tmp.setBlockLength(100);
        tmp.setBlockOffset(122);
        tmp.setPieceIndex(0);

        handler.putMessage(tmp);


    }
}

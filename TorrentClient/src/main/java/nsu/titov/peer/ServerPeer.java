package nsu.titov.peer;

import nsu.titov.messages.CancelMessage;
import nsu.titov.network_simple.ConnectionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerPeer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(Settings.DEFAULT_PORT));
        System.out.println(server.getLocalAddress());
        SocketChannel client = server.accept();

        ConnectionHandler handler = new ConnectionHandler(client);

        while (true) {
            handler.update();
            if (handler.ifIncoming()) {
                CancelMessage tmp = (CancelMessage) handler.getMessage();
                System.out.println(tmp.getId());
                System.out.println(tmp.getPieceIndex());
                System.out.println(tmp.getBlockOffset());
                System.out.println(tmp.getBlockLength());
            }
        }

    }
}

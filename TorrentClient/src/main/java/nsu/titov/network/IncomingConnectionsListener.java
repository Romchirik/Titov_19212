package nsu.titov.network;

import nsu.titov.Settings;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class IncomingConnectionsListener {

    private static final Logger logger = Logger.getLogger(IncomingConnectionsListener.class);
    private ServerSocketChannel incomingListener;


    public IncomingConnectionsListener() {
        try {
            Selector selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            incomingListener = ServerSocketChannel.open();
            incomingListener.socket().bind(new InetSocketAddress("localhost", Settings.DEFAULT_PORT));
        } catch (IOException e) {
            System.
            logger.error(String.format("Unable to open incoming connections socket at %s:%d", "localhost", Settings.MAX_INCOMING_CONNECTIONS));
            //TODO fix it!
            return;
        }
    }


    public void ifIncoming() {

    }

    public boolean connect() {

    }


}

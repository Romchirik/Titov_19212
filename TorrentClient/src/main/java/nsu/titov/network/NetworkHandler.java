package nsu.titov.network;

import nsu.titov.peer.Settings;

import java.net.InetAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.IdentityHashMap;
import java.util.Map;

public class NetworkHandler {

    public int INCOMING_PORT = Settings.DEFAULT_PORT;
    private Map<Integer, Connection> connections = new IdentityHashMap<>();

    private ServerSocketChannel incomingConnectionsSocket;

    private Selector selector;

    private NetworkHandler() {

    }


    public static NetworkHandler getInstance(int port) {
        NetworkHandler tmp = new NetworkHandler();

        return tmp;
    }

    public void enableIncomingConnections(int port) {

    }

    public void disableIncomingConnections() {

    }

    public void connect(InetAddress address){

    }
}

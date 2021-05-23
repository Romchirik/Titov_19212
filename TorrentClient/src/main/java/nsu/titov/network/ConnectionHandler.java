package nsu.titov.network;

import nsu.titov.Settings;

import java.io.IOException;
import java.net.ServerSocket;

public class ConnectionHandler {
    ServerSocket connectionsInput;

    public ConnectionHandler(){
        try {
            connectionsInput = new ServerSocket(Settings.MAX_INCOMING_CONNECTIONS)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

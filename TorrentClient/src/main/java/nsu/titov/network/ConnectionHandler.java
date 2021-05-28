package nsu.titov.network;

import nsu.titov.messages.Message;

import java.nio.channels.SocketChannel;

public class ConnectionHandler {
    private ConnectionState connectionState = ConnectionState.PENDING;
    private SocketChannel connection;


    public ConnectionHandler(SocketChannel connection) {
        this.connection = connection;
    }

    /**
     * Processes incoming data
     *
     * @return {@code null} if message is not ready and
     * {@link Message} if message accepted properly
     */
//    public Message handleInput() {
//        switch (connectionState){
//
//        }
//    }

    public boolean send(Message msg) {
        return true;
    }

    private void sendHandshake(){

    }

}

package nsu.titov.logic;

import nsu.titov.messages.PieceMessage;
import nsu.titov.messages.RequestMessage;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public interface MessageHandler {
    void handle(RequestMessage message, MessageReader handler, SelectionKey key);

    void handle(PieceMessage message, MessageReader handler, SelectionKey key);

}

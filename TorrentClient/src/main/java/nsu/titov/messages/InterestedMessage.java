package nsu.titov.messages;

import nsu.titov.logic.MessageHandler;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;

public class InterestedMessage extends Message {

    public InterestedMessage() {
        id = MessageId.INTERESTED;
    }

    public InterestedMessage(byte[] payload) {
        id = MessageId.INTERESTED;
    }

    @Override
    void generateBytes(byte[] bytes) {
    }

    @Override
    public  void handle(MessageHandler handler, MessageReader reader, SelectionKey key) {

    }
}

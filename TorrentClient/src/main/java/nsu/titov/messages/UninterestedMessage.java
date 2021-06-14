package nsu.titov.messages;

import nsu.titov.logic.MessageHandler;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;

public class UninterestedMessage extends Message {
    public UninterestedMessage() {
        id = MessageId.UNINTERESTED;
    }

    public UninterestedMessage(byte[] payload) {
        id = MessageId.UNINTERESTED;
    }

    @Override
    void generateBytes(byte[] bytes) {
    }

    @Override
    public void handle(MessageHandler handler, MessageReader reader, SelectionKey key) {

    }
}

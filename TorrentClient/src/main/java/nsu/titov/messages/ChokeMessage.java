package nsu.titov.messages;

import nsu.titov.logic.MessageHandler;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;

public class ChokeMessage extends Message {
    private final static int PAYLOAD_LENGTH = 0;

    public ChokeMessage() {
        id = MessageId.UNCHOKE;
    }

    public ChokeMessage(byte[] payload) {
        id = MessageId.CHOKE;
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    void generateBytes(byte[] bytes) {
    }

    @Override
    public void handle(MessageHandler handler, MessageReader reader, SelectionKey key) {

    }
}

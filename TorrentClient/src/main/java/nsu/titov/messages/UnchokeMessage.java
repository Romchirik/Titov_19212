package nsu.titov.messages;

import nsu.titov.logic.MessageHandler;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;

public class UnchokeMessage extends Message {

    public UnchokeMessage() {
        id = MessageId.UNCHOKE;
    }

    public UnchokeMessage(byte[] payload) {
        id = MessageId.UNCHOKE;
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    void generateBytes(byte[] bytes) {
    }

    @Override
    public  void handle(MessageHandler handler, MessageReader reader, SelectionKey key) {

    }
}

package nsu.titov.messages;

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
}

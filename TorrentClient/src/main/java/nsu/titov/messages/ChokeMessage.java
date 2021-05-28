package nsu.titov.messages;

public class ChokeMessage extends Message {
    private final static int PAYLOAD_LENGTH = 0;

    public ChokeMessage(){
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
}

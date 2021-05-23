package nsu.titov.messages;

public class ChokeMessage extends Message {
    private final static int PAYLOAD_LENGTH = 0;

    public ChokeMessage(byte[] payload) {
        id = MessageId.CHOKE;
    }
}

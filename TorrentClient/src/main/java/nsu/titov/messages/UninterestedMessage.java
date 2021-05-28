package nsu.titov.messages;

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
}

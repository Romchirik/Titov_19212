package nsu.titov.messages;

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
}

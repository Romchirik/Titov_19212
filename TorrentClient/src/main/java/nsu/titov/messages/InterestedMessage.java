package nsu.titov.messages;

public class InterestedMessage extends Message {

    public InterestedMessage(byte[] payload) {
        id = MessageId.INTERESTED;

    }
}

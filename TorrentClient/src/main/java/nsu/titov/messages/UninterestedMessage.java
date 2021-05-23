package nsu.titov.messages;

public class UninterestedMessage extends Message {
    public UninterestedMessage(byte[] payload) {
        id = MessageId.UNINTERESTED;
    }
}

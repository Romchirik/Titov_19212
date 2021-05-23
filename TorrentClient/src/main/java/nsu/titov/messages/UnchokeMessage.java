package nsu.titov.messages;

public class UnchokeMessage extends Message {
    public UnchokeMessage(byte[] payload) {
        id = MessageId.UNCHOKE;
    }
}

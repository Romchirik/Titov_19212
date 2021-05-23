package nsu.titov.messages;

public class HaveMessage extends Message {
    private final static int PAYLOAD_LENGTH = 4;

    private int pieceIndex = 0;

    public HaveMessage(byte[] payload) {
        payloadSize = PAYLOAD_LENGTH;
        id = MessageId.HAVE;
        this.payload = new byte[PAYLOAD_LENGTH];

        pieceIndex = ((0xFF & payload[1]) << 24) | ((0xFF & payload[2]) << 16) |
                ((0xFF & payload[3]) << 8) | (0xFF & payload[4]);
    }

    public int getPieceIndex() {
        return pieceIndex;
    }
}

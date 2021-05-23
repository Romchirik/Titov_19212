package nsu.titov.messages;

public class RequestMessage extends Message {
    private final static int PAYLOAD_LENGTH = 12;

    private final int pieceIndex;
    private final int blockOffset;
    private final int blockLength;

    public RequestMessage(byte[] payload) {
        payloadSize = PAYLOAD_LENGTH;
        id = MessageId.REQUEST;
        pieceIndex = ((0xFF & payload[1]) << 24) | ((0xFF & payload[2]) << 16) |
                ((0xFF & payload[3]) << 8) | (0xFF & payload[4]);
        blockOffset = ((0xFF & payload[5]) << 24) | ((0xFF & payload[6]) << 16) |
                ((0xFF & payload[7]) << 8) | (0xFF & payload[8]);
        blockLength = ((0xFF & payload[9]) << 24) | ((0xFF & payload[10]) << 16) |
                ((0xFF & payload[11]) << 8) | (0xFF & payload[12]);
    }

    public int getBlockLength() {
        return blockLength;
    }

    public int getPieceIndex() {
        return pieceIndex;
    }

    public int getBlockOffset() {
        return blockOffset;
    }
}

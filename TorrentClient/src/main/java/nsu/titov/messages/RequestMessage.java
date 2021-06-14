package nsu.titov.messages;

import nsu.titov.converters.ByteIntConverter;
import nsu.titov.logic.MessageHandler;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;

public class RequestMessage extends Message {
    private final static int PAYLOAD_LENGTH = 12;

    private int pieceIndex;
    private int blockOffset;
    private int blockLength;

    public RequestMessage() {
        finalDataSize = PAYLOAD_LENGTH + 5;
        id = MessageId.REQUEST;
    }

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

    public RequestMessage setPieceIndex(int pieceIndex) {
        this.pieceIndex = pieceIndex;
        return this;
    }

    public RequestMessage setBlockOffset(int blockOffset) {
        this.blockOffset = blockOffset;
        return this;
    }

    public RequestMessage setBlockLength(int blockLength) {
        this.blockLength = blockLength;
        return this;
    }

    @Override
    void generateBytes(byte[] bytes) {
        int[] raw = {pieceIndex, blockOffset, blockLength};
        byte[] bytes1 = ByteIntConverter.intToByte(raw, 0);
        System.arraycopy(bytes1, 0, bytes, 5, PAYLOAD_LENGTH);
    }

    @Override
    public void handle(MessageHandler handler, MessageReader reader, SelectionKey key) {
        handler.handle(this, reader, key);
    }
}

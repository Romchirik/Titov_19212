package nsu.titov.messages;

import nsu.titov.converters.ByteIntConverter;

import java.util.Arrays;

public class PieceMessage extends Message {

    private int pieceIndex = 0;
    private int blockOffset = 0;

    public PieceMessage() {
        id = MessageId.PIECE;
    }

    public PieceMessage(byte[] payload) {

        //remember, that here it isn't starting payload size
        payloadSize = payload.length - 9;

        id = MessageId.PIECE;
        pieceIndex = ((0xFF & payload[1]) << 24) | ((0xFF & payload[2]) << 16) |
                ((0xFF & payload[3]) << 8) | (0xFF & payload[4]);
        blockOffset = ((0xFF & payload[5]) << 24) | ((0xFF & payload[6]) << 16) |
                ((0xFF & payload[7]) << 8) | (0xFF & payload[8]);


        this.payload = new byte[payload.length - 9];
        System.arraycopy(payload, 9, this.payload, 0, payload.length - 9);
    }


    public int getPieceIndex() {
        return pieceIndex;
    }

    public int getBlockOffset() {
        return blockOffset;
    }

    public byte[] getBlock() {
        return Arrays.copyOfRange(payload, 9, payload.length);
    }

    @Override
    void generateBytes(byte[] bytes) {
        int[] raw = {pieceIndex, blockOffset};
        byte[] bytes1 = ByteIntConverter.intToByte(raw, 0);

        System.arraycopy(bytes1, 0, bytes, 5, 8);
        System.arraycopy(payload, 9, bytes, 5 + 8, payloadSize);
    }

}

package nsu.titov.messages;

import nsu.titov.logic.MessageHandler;
import nsu.titov.network.MessageReader;

import java.nio.channels.SelectionKey;
import java.util.Arrays;

public class BitfieldMessage extends Message {
    private final static int PAYLOAD_LENGTH = -1;

    public BitfieldMessage() {
        id = MessageId.BITFIELD;
    }

    public BitfieldMessage(byte[] payload) {

        id = MessageId.BITFIELD;
        payloadSize = payload.length - 1;
        finalDataSize = 5 + payloadSize;
        this.payload = new byte[payloadSize];
        System.arraycopy(payload, 1, this.payload, 0, payloadSize);
    }

    public boolean havePiece(int idx) {
        return 0 < (payload[(idx) / 8] & (0b10000000 >> idx % 8));
    }

    public void haveAll(){
        Arrays.fill(payload, (byte) 0xFF);
    }

    @Override
    void generateBytes(byte[] bytes) {
        System.arraycopy(payload, 0, bytes, 5, payloadSize);
    }

    @Override
    public void handle(MessageHandler handler, MessageReader reader, SelectionKey key) {

    }
}

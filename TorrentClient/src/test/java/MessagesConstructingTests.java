import nsu.titov.messages.*;
import nsu.titov.peer.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MessagesConstructingTests {

    @Test
    public void testHaveMessage() {

        byte[] payload1 = {(byte) 0x00, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};
        byte[] payload2 = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        byte[] payload3 = {(byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};

        HaveMessage message1 = new HaveMessage(payload1);
        HaveMessage message2 = new HaveMessage(payload2);
        HaveMessage message3 = new HaveMessage(payload3);

        Assertions.assertEquals(message1.getPieceIndex(), 2126327795);
        Assertions.assertEquals(message2.getPieceIndex(), 0);
        Assertions.assertEquals(message3.getPieceIndex(), -1);
    }

    @Test
    public void testBitfieldMessage() {
        byte[] payload1 = {(byte) 0x00, (byte) 0xF1, (byte) 0x00, (byte) 0x80, (byte) 0x01};

        BitfieldMessage message1 = new BitfieldMessage(payload1);

        Assertions.assertTrue(message1.havePiece(0));
        Assertions.assertTrue(message1.havePiece(1));
        Assertions.assertTrue(message1.havePiece(2));
        Assertions.assertTrue(message1.havePiece(3));
        Assertions.assertTrue(message1.havePiece(7));

        Assertions.assertTrue(message1.havePiece(31));
        Assertions.assertTrue(message1.havePiece(16));

    }

    @Test
    public void testRequestMessage() {

        byte[] payload1 = {(byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0xAD, (byte) 0xCB,
                (byte) 0x7C, (byte) 0xFA, (byte) 0xD6, (byte) 0x04,
                (byte) 0x07, (byte) 0x78, (byte) 0x6B, (byte) 0x85};

        RequestMessage message1 = new RequestMessage(payload1);

        Assertions.assertEquals(message1.getPieceIndex(), 1158603);
        Assertions.assertEquals(message1.getBlockOffset(), 2096813572);
        Assertions.assertEquals(message1.getBlockLength(), 125332357);

        RequestMessage message2 = new RequestMessage().setBlockLength(Settings.MAX_MESSAGE_SIZE).setBlockOffset(2).setPieceIndex(1);
        byte[] payload2 = message2.getBytes();


    }

    @Test
    public void testCancelMessage() {

        CancelMessage message1 = (CancelMessage) MessageFactory.createMessage(MessageId.CANCEL);
        byte[] payload = message1.setPieceIndex(100).setBlockOffset(300).setBlockLength(1200).getBytes();

        CancelMessage message2 = (CancelMessage) MessageFactory.createMessage(Arrays.copyOfRange(payload, 4, payload.length));

        Assertions.assertEquals(message2.getPieceIndex(), 100);
        Assertions.assertEquals(message2.getBlockLength(), 1200);
        Assertions.assertEquals(message2.getBlockOffset(), 300);

        payload = message1.getBytes();

        Assertions.assertEquals(payload[3], 17);
    }

}

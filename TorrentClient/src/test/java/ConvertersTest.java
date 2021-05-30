import nsu.titov.converters.ByteIntConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertersTest {

    @Test
    public void byteToIntConverterTest() {
        byte[] payload1 = {(byte) 0x00, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};
        byte[] payload2 = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};
        byte[] payload3 = {(byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};

        int[] output1 = ByteIntConverter.byteToInt(payload1, 1);
        int[] output2 = ByteIntConverter.byteToInt(payload2, 1);
        int[] output3 = ByteIntConverter.byteToInt(payload3, 1);

        Assertions.assertEquals(output1[0], 2126327795);
        Assertions.assertEquals(output1[1], 2126327795);
        Assertions.assertEquals(output2[0], 0);
        Assertions.assertEquals(output2[1], 2126327795);
        Assertions.assertEquals(output3[0], -1);
        Assertions.assertEquals(output3[1], 2126327795);
    }

    @Test
    public void intToByteConverterTest() {

        byte[] expecting1 = {(byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};
        byte[] expecting2 = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};
        byte[] expecting3 = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x7E, (byte) 0xBD, (byte) 0x2F, (byte) 0xF3};

        int[] payload1 = {2126327795, 2126327795};
        int[] payload2 = {0, 2126327795};
        int[] payload3 = {-1, 2126327795};

        byte[] output1 = ByteIntConverter.intToByte(payload1, 0);
        byte[] output2 = ByteIntConverter.intToByte(payload2, 0);
        byte[] output3 = ByteIntConverter.intToByte(payload3, 0);

        Assertions.assertArrayEquals(output1, expecting1);
        Assertions.assertArrayEquals(output2, expecting2);
        Assertions.assertArrayEquals(output3, expecting3);
    }
}

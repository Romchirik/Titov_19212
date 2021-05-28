package nsu.titov;

import java.nio.ByteBuffer;

public class ByteIntConverter {

    public static int[] byteToInt(byte[] data, int offset) {
        int[] tmp = new int[(data.length - offset) / 4];

        for (int i = 0; i < data.length - offset; i += 4) {
            tmp[i / 4] = ((0xFF & data[offset + i]) << 24) | ((0xFF & data[offset + i + 1]) << 16) |
                    ((0xFF & data[offset + i + 2]) << 8) | (0xFF & data[offset + i + 3]);
        }
        return tmp;
    }

    public static int[] byteToInt(byte[] data) {
        return byteToInt(data, 0);
    }

    public static byte[] intToByte(int[] data, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(data.length * 4);

        for (int i : data) {
            buffer.putInt(i);
        }

        return buffer.array();
    }

    public static byte[] intToByte(int data) {
        return ByteBuffer.allocate(4).putInt(data).array();
    }


}

package nsu.titov;

import java.nio.ByteBuffer;

public final class Helper {
    public static void copy(ByteBuffer src, int offset1, ByteBuffer dest, int offset2, int size) {
        for (int i = 0; i < size; i++) {
            dest.put(offset2 + i, src.get(offset1 + i));
        }
    }
}

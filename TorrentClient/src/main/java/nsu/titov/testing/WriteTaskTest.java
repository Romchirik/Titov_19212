package nsu.titov.testing;

import nsu.titov.filesystem.WriteTask;

import java.io.File;
import java.io.IOException;

public class WriteTaskTest {
    public static void main(String[] args) throws IOException {
        byte[] payload1 = {(byte) 0x0F, (byte) 0x00, (byte) 0x32, (byte) 0xDF, (byte) 0xAF, (byte) 0x65};
        byte[] payload2 = new byte[payload1.length];
        File testFile = File.createTempFile(".test", ".abc");

        WriteTask task = WriteTask.createTask(testFile, 0, payload1, 0, payload1.length);
        task.run();

        testFile.delete();
    }
}

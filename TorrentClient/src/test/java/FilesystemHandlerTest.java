import nsu.titov.filesystem.FilesystemHandler;
import nsu.titov.filesystem.MultiThreadFilesystemHandler;
import nsu.titov.filesystem.WriteTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilesystemHandlerTest {

    @Test
    public void writerTest() throws IOException, InterruptedException {
        //FIXME rewrite this test
        FilesystemHandler handler = new MultiThreadFilesystemHandler();
        File testFile = File.createTempFile(".temp", ".abc", new File("./"));
        testFile.setReadable(true);
        testFile.setWritable(true);

        byte[] payload1 = {(byte) 0x0F, (byte) 0x00, (byte) 0x32, (byte) 0xDF, (byte) 0xAF, (byte) 0x65};
        byte[] payload2 = new byte[payload1.length];


        handler.write(testFile, payload1, 0, payload1.length);
        Thread.sleep(500);
        InputStream input = new FileInputStream(testFile);
        int totalRead = input.read(payload2);


        Assertions.assertEquals(payload1.length, totalRead);
        Assertions.assertArrayEquals(payload1, payload2);

        testFile.delete();
    }

    @Test
    public void writerTaskTest() throws IOException {
        byte[] payload1 = {(byte) 0x0F, (byte) 0x00, (byte) 0x32, (byte) 0xDF, (byte) 0xAF, (byte) 0x65};
        byte[] payload2 = new byte[payload1.length];
        File testFile = File.createTempFile(".test", ".abc", new File("./"));

        WriteTask task = WriteTask.createTask(testFile, 0, payload1, 0, payload1.length);
        task.run();
    }
}

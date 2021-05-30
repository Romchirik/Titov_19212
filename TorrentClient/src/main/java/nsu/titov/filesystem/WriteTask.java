package nsu.titov.filesystem;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteTask implements Runnable {

    private final Logger logger = Logger.getLogger(getClass());

    File destination;
    private int fileOffset = 0;
    private final byte[] src;
    private int dataOffset = 0;
    private int length = 0;

    private WriteTask(File destination, int fileOffset, byte[] src, int dataOffset, int length) {
        this.destination = destination;
        this.fileOffset = fileOffset;
        this.src = src;
        this.dataOffset = dataOffset;
        this.length = length;
    }

    public static WriteTask createTask(File destination, int fileOffset, byte[] src, int dataOffset, int length) {
        if (destination == null || src == null) {
            return null;
        }
        return new WriteTask(destination, fileOffset, src, dataOffset, length);
    }

    @Override
    public void run() {
        try (RandomAccessFile output = new RandomAccessFile(destination, "rw")) {
            output.seek(fileOffset);
            output.write(src, dataOffset, length);
        } catch (IOException e) {
            System.out.println("hui");

        }

    }
}

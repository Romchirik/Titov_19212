package nsu.titov.filesystem;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteTask implements Runnable {

    private static final Logger logger = Logger.getLogger(WriteTask.class);
    private int length;
    private int offset;
    private byte[] data;
    private RandomAccessFile dest;


    private WriteTask() {
    }

    public WriteTask(RandomAccessFile dest, byte[] data, int offset, int length) {
        this.offset = offset;
        this.length = length;
        this.dest = dest;
        this.data = new byte[length];
        System.arraycopy(data, 0, this.data, 0, length);
    }

    @Override
    public void run() {
        try {
            dest.write(data, offset, length);
        } catch (IOException e) {
            logger.error(String.format("Error occurred while writing in %s, offset %d, length %d", dest, offset, length));
        }
    }

}

package nsu.titov.filesystem;

import nsu.titov.Settings;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilesystemWriter {

    private final HashMap<Integer, RandomAccessFile> openedFiles = new HashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(Settings.THREADPOOL_SIZE);

    public FilesystemWriter() {

    }

    public void addWriteTask(int fileId, byte[] data, int offset, int length) {

        executor.
    }
}

package nsu.titov.filesystem;

import nsu.titov.peer.Settings;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MultiThreadFilesystemHandler implements FilesystemHandler {
    private final ExecutorService executor = Executors.newFixedThreadPool(Settings.THREADPOOL_SIZE);

    @Override
    public byte[] read(File src, int offset, int length) {
        byte[] data = null;
        try (RandomAccessFile randomAccessStream = new RandomAccessFile(src, "r")) {
            data = new byte[length];
            randomAccessStream.read(data, offset, length);
        } catch (IOException ignored) {
        }
        return data;
    }

    @Override
    public boolean write(File dest, byte[] data, int offset, int length) {
        WriteTask task = WriteTask.createTask(dest, 0, data, 0, length);
        if (task == null) {
            return false;
        }
        executor.submit(task);
        System.out.println("Written");
        return true;
    }

    @Override
    public void merge(String filename) {

    }
}

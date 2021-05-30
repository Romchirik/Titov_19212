package nsu.titov.filesystem;

import java.io.File;

public interface FilesystemHandler {

    byte[] read(File src, int offset, int length);

    boolean write(File dest, byte[] data, int offset, int length);

    void merge(String filename);
}

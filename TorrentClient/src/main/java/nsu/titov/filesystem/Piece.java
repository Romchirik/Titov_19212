package nsu.titov.filesystem;

import java.io.File;

public class Piece {
    private PieceStatus pieceStatus = PieceStatus.PENDING;
    private final int length;
    private final File file;

    public Piece(File associatedFile, int length) {
        this.file = associatedFile;
        this.length = length;
    }

    public File getFile() {
        return file;
    }

    public PieceStatus getPieceStatus() {
        return pieceStatus;
    }

    public void setPieceStatus(PieceStatus pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    public int getLength() {
        return length;
    }
}

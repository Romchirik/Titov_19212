package nsu.titov.logic;

import nsu.titov.peer.Settings;
import org.apache.log4j.Logger;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FilesystemManager {

    private int numPieces = 0;
    private static final Logger logger = Logger.getLogger(FilesystemManager.class);
    private byte[][] pieces;


    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
        pieces = new byte[numPieces][];
    }

    byte[] readPiece(File file, int index, int size) {

        int totalSize = (int) file.length();
        int maxIndex = totalSize / Settings.MAX_MESSAGE_SIZE;
        int availableBytes = size;
        if (index > maxIndex) {
            logger.error("Invalid piece index index > maxIndex");
            return null;
        }
        if (maxIndex == index) {
            availableBytes = totalSize % Settings.MAX_MESSAGE_SIZE;
        }


        byte[] piece = new byte[availableBytes];
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(Settings.MAX_MESSAGE_SIZE * index);
            randomAccessFile.read(piece, 0, availableBytes);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            logger.error("Error occurred while reading a piece " + index);
            return null;
        }
        return piece;
    }

    boolean validatePiece(byte[] piece, byte[] hash) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(Settings.HASHING_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Hashing algorithm not found");
            return false;
        }

        byte[] actual = digest.digest(piece);
        return Arrays.equals(actual, hash);
    }

    public void addPiece(int index, byte[] piece){
        pieces[index] = piece;
    }


    public void merge(File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        try(FileOutputStream stream = new FileOutputStream(file)){
            for(int i = 0; i < pieces.length; i ++){
                stream.write(pieces[i]);
            }
        }
    }
}

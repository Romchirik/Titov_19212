package nsu.titov.generator;

import com.dampcake.bencode.Bencode;
import com.dampcake.bencode.BencodeOutputStream;
import nsu.titov.metainfo.MetaData;
import nsu.titov.peer.Settings;
import org.apache.log4j.Logger;
import picocli.CommandLine;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "torrent-generator")
public class TorrentGenerator implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "FILE", description = "generates .torrent file for given FILE")
    File target;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "print this help screen")
    boolean help;

    private static final Logger logger = Logger.getLogger(TorrentGenerator.class);

    public static void main(String... args) {
        System.exit(new CommandLine(new TorrentGenerator()).execute(args));
    }


    @Override
    public Integer call() throws Exception {
        MetaData metadata = new MetaData();

        metadata.name = target.getName();
        metadata.length = target.length();

        int numPieces = (int) (metadata.length / Settings.MAX_MESSAGE_SIZE + 1);

        metadata.pieceLength = Settings.MAX_MESSAGE_SIZE;

        metadata.pieces = generateHashArray(target, numPieces);

        storeMetadata(metadata);
        return 0;
    }

    private void storeMetadata(MetaData metadata) throws IOException, ClassNotFoundException {
        String filename = metadata.name + ".torrent";
        File torrent = new File(filename);

        try {
            if (!torrent.createNewFile()) {
                System.out.println("File already exists");
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("Unknown error, looks like you don't have a permission to create files here");
            System.exit(0);
        }

        Bencode bencoder = new Bencode();
        HashMap<Object, Object> root = new HashMap<>();
        HashMap<Object, Object> info = new HashMap<>();

        info.put("name", metadata.name);
        info.put("piece length", metadata.pieceLength);
        info.put("length", metadata.length);
        info.put("pieces", Arrays.toString(metadata.pieces));

        root.put("announce", "");
        root.put("info", info);


        try (OutputStream output = new FileOutputStream(torrent)) {
            BencodeOutputStream bencodeOutput = new BencodeOutputStream(output);
            bencodeOutput.writeDictionary(root);
        } catch (IOException ignored) {
            logger.error("Unable to store metadata in file");
            System.exit(0);
        }

    }

    private byte[] generateHashArray(File target, int numPieces) throws NoSuchAlgorithmException {
        int pieceSize = Settings.MAX_MESSAGE_SIZE;
        byte[] result = new byte[numPieces * 20];
        MessageDigest md = MessageDigest.getInstance(Settings.HASHING_ALGORITHM);

        try (RandomAccessFile file = new RandomAccessFile(target, "r")) {
            int writeIndex = 0;
            int start = 0;
            int end = pieceSize;
            byte[] piece = new byte[pieceSize];

            while (end < target.length()) {

                file.read(piece, 0, pieceSize);
                System.arraycopy(md.digest(piece), 0, result, writeIndex, 20);

                writeIndex += 20;
                start += pieceSize;
                end += pieceSize;
            }

            piece = new byte[(int) (file.length() - start)];
            file.read(piece, 0, (int) file.length() - start);
            System.arraycopy(md.digest(piece), 0, result, writeIndex, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}

package nsu.titov.metainfo;


import com.dampcake.bencode.BencodeInputStream;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class MetaDataLoader {

    private static final Logger logger = Logger.getLogger(MetaDataLoader.class);

    public static MetaData decode(File dataFile) throws IOException {

        Map<String, Object> metaInfoMap;
        MetaData metadata = new MetaData();
        BencodeInputStream stream;
        try {
            stream = new BencodeInputStream(new FileInputStream(dataFile));
        } catch (FileNotFoundException ignored) {
            return null;
        }

        try {
            metaInfoMap = stream.readDictionary();
        } catch (IOException e) {
            return null;
        }

        //extracting data
        Map<String, Object> usableInfo = (Map<String, Object>) metaInfoMap.get("info");
        metadata.length = (long) usableInfo.get("length");
        metadata.name = (String) usableInfo.get("name");
        metadata.pieceLength = (long) usableInfo.get("piece length");

        int numPieces = (int) (metadata.length / metadata.pieceLength + 1);
        String rawHash = (String) usableInfo.get("pieces");

        if (metadata.name == null ||
                metadata.length == 0 ||
                metadata.pieceLength == 0
        ) {
            return null;
        }

        metadata.pieces = getHashes(rawHash, numPieces);
        return metadata;
    }

    private static byte[] getHashes(String string, int numPieces) throws IOException {
        byte[] result = new byte[numPieces * 20];
        String tmp = string.replaceAll(",", "");
        tmp = tmp.replaceAll("\\[", "");
        tmp = tmp.replaceAll("\\]", "");

        String[] bytes = tmp.split(" ");

        for (int i = 0; i < numPieces * 20; i++) {
            result[i] = Byte.parseByte(bytes[i]);
        }

        return result;
    }

}

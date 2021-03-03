package ru.nsu.context;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PlayfieldLoader {
    static Logger logger = Logger.getLogger(PlayfieldLoader.class);

    static public Playfield loadField(String filename) throws IOException {
        logger.debug(String.format("Trying to load playfield from %s", filename));
        Playfield tmp_playfield = new Playfield();

        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            lines.forEach((line) -> {
                line.chars().forEach((instruction) -> {
                    logger.debug(String.format("Loaded symbol: %c", instruction));
                    tmp_playfield.addSymbol((char) instruction);
                });
                tmp_playfield.addSymbol('\n');
                logger.debug("Loaded line break");
            });
        }
        logger.debug("Playfield loaded successfully");
        return tmp_playfield;
    }

}
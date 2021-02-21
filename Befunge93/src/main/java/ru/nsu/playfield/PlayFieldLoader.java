package ru.nsu.playfield;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PlayFieldLoader {
    static Logger logger = Logger.getLogger(PlayFieldLoader.class);

    private PlayFieldLoader() {
    }

    static void loadField(String filename) throws IOException {


        Playfield tmp_playfield = new Playfield();
        try (Stream<String> lines = Files.lines(Path.of(filename));) {
            int current_row = 0;
            int current_column = 0;
            lines.forEach((line) -> {
                line.chars().forEach((instruction) -> {
                    tmp_playfield.setInstruction(current_row, current_column, (char) instruction);
                });
            });
        } catch (IOException e){
            logger.error(e.getMessage());
        }

    }

}
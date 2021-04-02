package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MapLoader {
    public static Map loadMap() throws IOException {
        Map tmp_map = new Map();
        ArrayList<GameObject> tmp = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(Settings.MAP_LOCATION))) {
            lines.forEach(line -> {
                line.chars().forEach(symbol -> {
                    //TODO добавить пропертисы (возможно)
                    switch (symbol) {
                        case ' ' -> tmp.add(new Void(0, 0));
                        case '#', ',' -> tmp.add(new Wall(0, 0));
                        case '.' -> tmp.add(new Food(0, 0));
                    }
                });
            });


        }
        finalizeMap(tmp);
        tmp_map.map = tmp;
        return tmp_map;
    }

    private static void finalizeMap(ArrayList<GameObject> tmpMap) {
        for (int i = 0; i < tmpMap.size(); i++) {
            tmpMap.get(i).setLogicalX(i % Settings.MAP_LOGICAL_WIDTH);
            tmpMap.get(i).setLogicalY(i / Settings.MAP_LOGICAL_WIDTH);
        }

    }
}

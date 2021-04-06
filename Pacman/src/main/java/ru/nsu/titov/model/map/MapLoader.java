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
        Map tmpMap = new Map();
        ArrayList<GameObject> food = new ArrayList<>();
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
        tmpMap.map = tmp;
        tmpMap.food = food;
        finalizeMap(tmpMap);
        return tmpMap;
    }

    private static void finalizeMap(Map tmpMap) {
        for (int i = 0; i < tmpMap.map.size(); i++) {
            GameObject tmpObject = tmpMap.map.get(i);
            tmpObject.setX(i % Settings.MAP_LOGICAL_WIDTH);
            tmpObject.setY(i / Settings.MAP_LOGICAL_WIDTH);

            if (tmpObject instanceof Food) {
                tmpMap.foodsCount++;
                tmpMap.food.add(tmpObject);
                tmpMap.map.set(i, new Void(tmpObject.getX(), tmpObject.getY()));

            }
        }

    }
}

package ru.nsu.titov.model.playfield;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ObjectId;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FieldLoader {
    public static GameField loadMap(String mapName) throws IOException {
        GameField tmpMap = new GameField();
        try (InputStream lines = FieldLoader.class.getResourceAsStream(String.format("/maps/%s.pcm", mapName))) {

            int currentLine = 0;
            int currentRow = 0;

            Scanner scanner = new Scanner(lines);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int e : line.chars().toArray()) {
                    switch (e) {
                        case '#' -> tmpMap.playfield.add(new Wall(0, 0));
                        case '.' -> tmpMap.playfield.add(new Food(0, 0));
                        case '*' -> tmpMap.playfield.add(new Energizer(0, 0));
                        case 'p' -> {
                            tmpMap.pinkyStartX = currentLine;
                            tmpMap.pinkyStartY = currentRow;
                            tmpMap.playfield.add(new Void(0, 0));
                        }
                        case 'i' -> {
                            tmpMap.inkyStartX = currentLine;
                            tmpMap.inkyStartY = currentRow;
                            tmpMap.playfield.add(new Void(0, 0));
                        }
                        case 'c' -> {
                            tmpMap.clydeStartX = currentLine;
                            tmpMap.clydeStartY = currentRow;
                            tmpMap.playfield.add(new Void(0, 0));
                        }
                        case 'b' -> {
                            tmpMap.blinkyStartX = currentLine;
                            tmpMap.blinkyStartY = currentRow;
                            tmpMap.playfield.add(new Void(0, 0));
                        }
                        case 'P' -> {
                            tmpMap.pacmanStartX = currentLine;
                            tmpMap.pacmanStartY = currentRow;
                            tmpMap.playfield.add(new Void(0, 0));
                        }
                        default -> tmpMap.playfield.add(new Void(0, 0));
                    }
                    currentRow++;
                }
                currentRow = 0;
                currentLine++;
            }
        }
        finalizeMap(tmpMap);
        return tmpMap;
    }

    private static void finalizeMap(GameField tmpMap) {

        for (int i = 0; i < tmpMap.playfield.size(); i++) {
            GameObject tmpObject = tmpMap.playfield.get(i);
            tmpObject.setX(i % Settings.MAP_LOGICAL_WIDTH);
            tmpObject.setY(i / Settings.MAP_LOGICAL_WIDTH);
        }
        tmpMap.playfield.forEach(e -> {
            if (e.getID() == ObjectId.FOOD) {
                tmpMap.foodsLeft++;
            }
        });
        for (int i = 0; i < tmpMap.playfield.size(); i++) {
            byte neighbours = 0b00000000;
            GameObject tmp = tmpMap.playfield.get(i);
            if (tmp.getID() == ObjectId.WALL) {
                Wall wall = (Wall) tmp;

                //up
                if (tmpMap.getObjectAt(wall.getX(), wall.getY() - 1).getID() == ObjectId.WALL) {
                    neighbours |= 1;
                }

                //left
                if (tmpMap.getObjectAt(wall.getX() + 1, wall.getY()).getID() == ObjectId.WALL) {
                    neighbours |= 1 << 1;
                }

                //down
                if (tmpMap.getObjectAt(wall.getX(), wall.getY() + 1).getID() == ObjectId.WALL) {
                    neighbours |= 1 << 2;
                }

                //right
                if (tmpMap.getObjectAt(wall.getX() - 1, wall.getY()).getID() == ObjectId.WALL) {
                    neighbours |= 1 << 3;
                }
                switch (neighbours) {
                    case 0b00000000 -> wall.setType(WallType.SINGLE);
                    case 0b00000001 -> wall.setType(WallType.UP);
                    case 0b00000010 -> wall.setType(WallType.RIGHT);
                    case 0b00000011 -> wall.setType(WallType.CORNER_UP_RIGHT);
                    case 0b00000100 -> wall.setType(WallType.DOWN);
                    case 0b00000101 -> wall.setType(WallType.VERTICAL);
                    case 0b00000110 -> wall.setType(WallType.CORNER_RIGHT_DOWN);
                    case 0b00000111 -> wall.setType(WallType.T_RIGHT);
                    case 0b00001000 -> wall.setType(WallType.LEFT);
                    case 0b00001001 -> wall.setType(WallType.CORNER_LEFT_UP);
                    case 0b00001010 -> wall.setType(WallType.HORIZONTAL);
                    case 0b00001011 -> wall.setType(WallType.T_UP);
                    case 0b00001100 -> wall.setType(WallType.CORNER_DOWN_LEFT);
                    case 0b00001101 -> wall.setType(WallType.T_LEFT);
                    case 0b00001110 -> wall.setType(WallType.T_DOWN);
                    case 0b00001111 -> wall.setType(WallType.X_SHAPED);
                }
            }


        }


    }
}

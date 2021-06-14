package ru.nsu.titov.model.playfield;


import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ObjectId;

import java.util.ArrayList;
import java.util.List;


final public class GameField {
    //TODO запилить размеры карты в файл с картой и загружать через маплоадер

    public int pacmanStartX = 0;
    public int pacmanStartY = 0;

    public int blinkyStartX = 0;
    public int blinkyStartY = 0;

    public int inkyStartX = 0;
    public int inkyStartY = 0;

    public int clydeStartX = 0;
    public int clydeStartY = 0;

    public int pinkyStartX = 0;
    public int pinkyStartY = 0;

    public int width = Settings.MAP_LOGICAL_WIDTH;
    public int height = Settings.MAP_LOGICAL_HEIGHT;

    int foodsLeft = 0;
    ArrayList<GameObject> playfield = new ArrayList<>();

    public GameObject getObjectAt(int x, int y) {
        return playfield.get(translateCoordinates(x, y));
    }

    public void setObjectAt(GameObject object, int x, int y) {
        if (playfield.get(translateCoordinates(x, y)).getID() == ObjectId.FOOD) {
            foodsLeft--;
        }
        playfield.set(translateCoordinates(x, y), object);
    }

    public int getFoodsLeft() {
        return foodsLeft;
    }

    public ArrayList<GameObject> getAll() {
        return playfield;
    }


    private int translateCoordinates(int x, int y) {
        if (x < 0) x = x + width;
        if (y < 0) y = y + height;

        if (x >= width) x = x % width;
        if (y >= height) y = y % height;
        return y * width + x;
    }

    public boolean acceptMove(int x, int y, int nextX, int nextY, ObjectId id) {
        return playfield.get(translateCoordinates(nextX, nextY)).getID() != ObjectId.WALL;
    }

    public int countAvailableDirs(int x, int y) {
        int i = 0;

        if (getObjectAt(x - 1, y).getID() != ObjectId.WALL) i++;
        if (getObjectAt(x + 1, y).getID() != ObjectId.WALL) i++;
        if (getObjectAt(x, y + 1).getID() != ObjectId.WALL) i++;
        if (getObjectAt(x, y - 1).getID() != ObjectId.WALL) i++;
        return i;
    }

    public List<Direction> getAvailableDirs(int x, int y, ObjectId id) {
        List<Direction> tmp = new ArrayList<>();
        if (getObjectAt(x - 1, y).getID() != ObjectId.WALL) tmp.add(Direction.LEFT);
        if (getObjectAt(x + 1, y).getID() != ObjectId.WALL) tmp.add(Direction.RIGHT);
        if (getObjectAt(x, y + 1).getID() != ObjectId.WALL) tmp.add(Direction.DOWN);
        if (getObjectAt(x, y - 1).getID() != ObjectId.WALL) tmp.add(Direction.UP);

        return tmp;
    }

    public void normalizeCoords(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (x < 0) x = x + width;
        if (y < 0) y = y + height;

        if (x >= width) x = x % width;
        if (y >= height) y = y % height;

        object.setX(x);
        object.setY(y);
    }
}

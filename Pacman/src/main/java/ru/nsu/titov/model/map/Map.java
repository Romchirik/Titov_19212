package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;

import java.util.ArrayList;

public class Map {
    //TODO запилить размеры карты в файл с картой и загружать через маплоадер
    int width = Settings.MAP_LOGICAL_WIDTH;
    int height = Settings.MAP_LOGICAL_HEIGHT;

    int foodsCount = 0;

    ArrayList<GameObject> food;
    ArrayList<GameObject> map;

    public GameObject getObjectAt(int x, int y) {
        return map.get(y * width + x);
    }

    public void setObjectAt(GameObject object, int x, int y) {
        map.set(y * width + x, object);
    }

    public static int normalizeX(int logicalX) {
        int tmpX = logicalX % Settings.MAP_LOGICAL_WIDTH;
        return tmpX < 0 ? tmpX + Settings.MAP_LOGICAL_WIDTH : tmpX;
    }

    public static int normalizeY(int logicalY) {
        int tmpY = logicalY % Settings.MAP_LOGICAL_HEIGHT;
        return tmpY < 0 ? tmpY + Settings.MAP_LOGICAL_HEIGHT : tmpY;
    }

    public int getFoodsLeft(){
        return food.size();
    }

    public void removeFoodAt(int x, int y){

    }
    //TODO удалить после доделывания отрисовки
    public ArrayList<GameObject> getMap() {
        return map;
    }

    public ArrayList<GameObject> getFood() {
        return food;
    }
}

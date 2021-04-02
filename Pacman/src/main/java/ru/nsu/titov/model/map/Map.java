package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;

import java.util.ArrayList;

public class Map {
    //TODO запилить размеры карты в файл с картой и загружать через маплоадер
    int width = Settings.MAP_LOGICAL_WIDTH;
    int height = Settings.MAP_LOGICAL_HEIGHT;

    ArrayList<GameObject> map;

    public GameObject getObjectAt(int x, int y) {
        return map.get(x * width + y);
    }

    public void setObjectAt(GameObject object, int x, int y) {
        map.set(y * width + x, object);
    }

    public static int normalizeLogicalX(int logicalX) {
        int tmpX = logicalX % Settings.MAP_LOGICAL_WIDTH;
        return tmpX < 0 ? tmpX + Settings.MAP_LOGICAL_WIDTH : tmpX;
    }

    public static int normalizeLogicalY(int logicalY) {
        int tmpY = logicalY % Settings.MAP_LOGICAL_HEIGHT;
        return tmpY < 0 ? tmpY + Settings.MAP_LOGICAL_HEIGHT : tmpY;
    }

    public ArrayList<GameObject> getMap() {
        return map;
    }
}

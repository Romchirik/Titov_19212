package ru.nsu.titov.model;

import ru.nsu.titov.model.GameObject;

abstract public class Ghost extends GameObject {

    public Ghost(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    void onCollide(GameObject obj) {

    }


}

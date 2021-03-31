package ru.nsu.titov.model;

abstract public class Ghost extends GameObject {

    public Ghost(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    void onCollide(GameObject obj) {

    }


}

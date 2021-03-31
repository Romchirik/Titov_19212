package ru.nsu.titov.model;

public class Void extends GameObject{

    public Void(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick(ModelController model) {

    }

    @Override
    void onCollide(GameObject obj) {

    }
}

package ru.nsu.titov.model;

import ru.nsu.titov.model.GameObject;

public class PowerPill extends GameObject {

    public PowerPill(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    void tick(ModelController model) {

    }

    @Override
    void onCollide(GameObject obj) {

    }
}

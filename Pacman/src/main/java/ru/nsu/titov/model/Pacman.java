package ru.nsu.titov.model;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;

public class Pacman extends GameObject {
    int lives = 3;
    int powerMode = 0;
    public Pacman(int x, int y, int width, int height) {
        super(x, y, width, height);
        ID = ObjectId.PACMAN;
    }

    @Override
    protected void tick(ModelController model) {
        if(powerMode > 0){
            powerMode--;
        }
        switch (direction) {
            case DOWN -> y += velocity;
            case UP -> y -= velocity;
            case LEFT -> x -= velocity;
            case RIGHT -> x += velocity;
        }
    }

    @Override
    void onCollide(GameObject obj) {

    }

}

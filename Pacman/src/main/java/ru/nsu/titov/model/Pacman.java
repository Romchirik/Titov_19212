package ru.nsu.titov.model;

public class Pacman extends GameObject {
    int lives = 3;

    public Pacman(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    void tick() {
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

    void setDead(){

    }


}

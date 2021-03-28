package ru.nsu.titov.model.entities;

import java.awt.*;

public abstract class GameObject {

    //VELOCITY PER TICK
    int ID = 0;
    int velocity = 0;
    int x = 0;
    int y = 0;
    Direction direction = Direction.UP;

    public GameObject(int ID, int velocity, int x, int y, Direction direction) {
        this.ID = ID;
        this.velocity = velocity;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public boolean checkCollide(Integer x_other, Integer y_other) {
        return x == x_other && y == y_other;
    }

    public void move(Direction dir) {
        switch (dir) {
            case UP -> y -= velocity;
            case DOWN -> y += velocity;
            case LEFT -> x -= velocity;
            case RIGHT -> x += velocity;
        }
    }

    public void tick() {

    }

    public abstract void onCollide(GameObject collidedEntity);


    public int getID() {
        return ID;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }
}

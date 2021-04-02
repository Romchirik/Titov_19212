package ru.nsu.titov.model;

import ru.nsu.titov.controller.Settings;

import java.awt.*;

import static ru.nsu.titov.model.Direction.DOWN;
import static ru.nsu.titov.model.Direction.UNDEFINED;
import static ru.nsu.titov.model.ObjectId.DEFAULT;

//TODO переделать все на дискретную логику
//TODO добавить метод tickBack()
abstract public class GameObject {
    protected int x;
    protected int y;

    protected int velocity;

    protected ObjectId ID = DEFAULT;
    protected Direction direction = UNDEFINED;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean checkCollision(GameObject obj1, GameObject obj2) {
        return obj1.x == obj2.y && obj1.y == obj2.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ObjectId getID() {
        return ID;
    }

    public Direction getDirection() {
        return direction;
    }

    //TODO сделать интерфейс для модели (возможно)
    public abstract void tick(ModelController model);

    //TODO временные меры для ускорения разработки
    public abstract void paint(Graphics g);

    abstract void onCollide(GameObject object, ModelController model);
}

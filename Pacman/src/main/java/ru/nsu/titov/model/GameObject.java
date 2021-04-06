package ru.nsu.titov.model;

import static ru.nsu.titov.model.Direction.UNDEFINED;
import static ru.nsu.titov.model.ObjectId.DEFAULT;

//TODO переделать все на дискретную логику
//TODO добавить метод tickBack()
abstract public class GameObject implements Paintable {
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
        return obj1.x == obj2.x && obj1.y == obj2.y;
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

    public int getNextX() {
        return switch (direction) {
            case RIGHT -> x + 1;
            case LEFT -> x - 1;
            default -> x;
        };
    }

    public int getNextY() {
        return switch (direction) {
            case UP -> y - 1;
            case DOWN -> y + 1;
            default -> y;
        };
    }

    //TODO сделать интерфейс для модели (возможно)
    public abstract void tick(ModelController model);

    public abstract void onCollide(GameObject object, ModelController model);
}

package ru.nsu.titov.model;

import java.awt.*;

import static ru.nsu.titov.model.Direction.DOWN;
import static ru.nsu.titov.model.ObjectId.DEFAULT;

public abstract class GameObject {

    protected int x, y;
    protected int width, height;
    protected ObjectId ID = DEFAULT;
    protected Direction direction = DOWN;
    protected int velocity = 0;


    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    static public boolean checkCollision(GameObject obj1, GameObject obj2) {
        if (obj1 != obj2) {
            return obj1.x < obj2.x + obj2.width &&
                    obj1.x + obj1.width > obj2.x &&
                    obj1.y < obj2.y + obj2.height &&
                    obj1.y + obj1.height > obj2.y;
        } else {
            return true;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public ObjectId getID() {
        return ID;
    }

    public void setID(ObjectId ID) {
        this.ID = ID;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    abstract void tick(ModelController model);

    abstract void onCollide(GameObject obj);

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);

    }
}


package ru.nsu.titov.model.entities.pacman;

import ru.nsu.titov.model.entities.Direction;
import ru.nsu.titov.model.entities.GameObject;

public class Pacman extends GameObject {

    boolean rageMode = false;
    boolean eaten = false;

    public Pacman(int ID, int velocity, int x, int y, Direction direction) {
        super(ID, velocity, x, y, direction);
    }

    @Override
    public void onCollide(GameObject collidedEntity) {

    }

    @Override
    public void move(Direction dir) {

    }

    public void setRageMode(boolean rageMode) {
        this.rageMode = rageMode;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public boolean isRageMode() {
        return rageMode;
    }

    public boolean isEaten() {
        return eaten;
    }

}

package ru.nsu.titov.model.entities.fixed;

import ru.nsu.titov.model.entities.Direction;
import ru.nsu.titov.model.entities.GameObject;

public class Wall extends GameObject{

    public Wall(int ID, int velocity, int x, int y, Direction direction) {
        super(ID, velocity, x, y, direction);
    }

    @Override
    public void onCollide(GameObject collidedEntity) {

    }
}

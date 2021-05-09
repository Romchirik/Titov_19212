package ru.nsu.titov.model.playfield;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Door extends GameObject {
    public Door(int x, int y) {
        super(x, y);
        ID = ObjectId.DOOR;
    }

    public Door(int x, int y, Direction direction) {
        super(x, y);
        ID = ObjectId.DOOR;
        this.direction = direction;
    }

    @Override
    public void tick(Model model) {

    }

    @Override
    public void onCollide(GameObject object, Model model) {

    }
}

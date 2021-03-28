package ru.nsu.titov.model.entities.ghosts;

import ru.nsu.titov.model.entities.Direction;

public class Pinky extends Ghost {
    public Pinky(int ID, int velocity, int x, int y, Direction direction) {
        super(ID, velocity, x, y, direction);
    }

    @Override
    public void move(Direction dir) {

    }
}

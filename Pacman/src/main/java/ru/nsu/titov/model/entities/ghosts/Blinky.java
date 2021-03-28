package ru.nsu.titov.model.entities.ghosts;

import ru.nsu.titov.model.entities.Direction;

public class Blinky extends Ghost {
    public Blinky(int ID, int velocity, int x, int y, Direction direction) {
        super(ID, velocity, x, y, direction);
    }

    @Override
    public void move(Direction dir) {

    }
}

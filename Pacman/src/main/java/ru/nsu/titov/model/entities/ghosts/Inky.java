package ru.nsu.titov.model.entities.ghosts;

import ru.nsu.titov.model.entities.Direction;

public class Inky extends Ghost {
    public Inky(int ID, int velocity, int x, int y, Direction direction) {
        super(ID, velocity, x, y, direction);
    }

    @Override
    public void move(Direction dir) {

    }
}

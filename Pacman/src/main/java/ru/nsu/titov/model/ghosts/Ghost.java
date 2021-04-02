package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;

abstract public class Ghost extends GameObject {
    public Ghost(int logicalX, int logicalY) {
        super(logicalX, logicalY);
    }

    abstract void updateTactic(ModelController model);
}

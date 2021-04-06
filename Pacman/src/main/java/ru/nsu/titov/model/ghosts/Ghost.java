package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;

import static ru.nsu.titov.model.ghosts.GhostState.EATEN;
import static ru.nsu.titov.model.ghosts.GhostState.SCATTER;

abstract public class Ghost extends GameObject {

    GhostState currentState = SCATTER;

    public Ghost(int logicalX, int logicalY) {
        super(logicalX, logicalY);
    }

    @Override
    public void onCollide(GameObject object, ModelController model) {
        if (object.getID() == ObjectId.PACMAN) {
            switch (currentState) {
                case FRIGHTENED -> currentState = EATEN;
                case CHASE, SCATTER -> model.setPacmanEaten(true);
            }
        }
    }

    abstract void updateTactic(ModelController model);
}

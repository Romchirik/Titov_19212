package ru.nsu.titov.model.entities.ghosts;

import ru.nsu.titov.model.entities.Direction;
import ru.nsu.titov.model.entities.GameObject;
import ru.nsu.titov.model.entities.pacman.Pacman;

public abstract class Ghost extends GameObject {

    GhostStates state = GhostStates.SCATTER;

    public Ghost(int ID, int velocity, int x, int y, Direction direction) {
        super(ID, velocity, x, y, direction);
    }

    @Override
    public void onCollide(GameObject collidedEntity) {
        if (collidedEntity instanceof Pacman){
            switch (state){
                case CHASE, SCATTER -> {
                    ((Pacman) collidedEntity).setEaten(true);
                }
                case FRIGHTENED -> {
                    state = GhostStates.EATEN;
                }
                case EATEN -> {

                }
            }
        }
    }

    public void setState(GhostStates state) {
        this.state = state;
    }

    public GhostStates getState() {
        return state;
    }
}

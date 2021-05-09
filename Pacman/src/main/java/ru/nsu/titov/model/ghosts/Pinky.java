package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Pinky extends Ghost {

    public Pinky(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.PINKY;

        scatterX = 0;
        scatterY = 0;
    }

    public Pinky(int logicalX, int logicalY, int velocity) {
        super(logicalX, logicalY);
        ID = ObjectId.PINKY;
        this.velocity = velocity;
    }

    @Override
    void updateChaseTactic(Model model) {
        GameObject pacman = model.getPacmanPosition();
        switch (pacman.getDirection()) {
            case LEFT -> this.direction = getPreferredDirection(pacman.getX() - 4, pacman.getY(), model.getGameField());
            case RIGHT -> this.direction = getPreferredDirection(pacman.getX() + 4, pacman.getY(), model.getGameField());
            case DOWN -> this.direction = getPreferredDirection(pacman.getX(), pacman.getY() + 4, model.getGameField());
            case UP -> this.direction = getPreferredDirection(pacman.getX() - 4, pacman.getY() - 4, model.getGameField());
        }
    }
}

package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.model.playfield.GameField;

import java.util.List;
import java.util.Random;

import static ru.nsu.titov.model.ghosts.GhostState.CHASE;
import static ru.nsu.titov.model.ghosts.GhostState.EATEN;

abstract public class Ghost extends GameObject {
    protected int scatterX = 0;
    protected int scatterY = 0;

    GhostState currentState = CHASE;
    Random rand = new Random();


    public Ghost(int logicalX, int logicalY) {
        super(logicalX, logicalY);
    }

    protected boolean tacticUpdateRequired = false;

    @Override
    public void onCollide(GameObject object, Model model) {
        if (object.getID() == ObjectId.PACMAN) {
            switch (currentState) {
                case FRIGHTENED -> currentState = EATEN;
                default -> {
                }
            }
        }
    }

    @Override
    public void tick(Model model) {
        ticksPassed++;
        if (!stopFlag && ticksPassed == velocity) {
            tacticUpdateRequired = true;
            ticksPassed = 0;
            switch (direction) {
                case DOWN -> y += 1;
                case UP -> y -= 1;
                case LEFT -> x -= 1;
                case RIGHT -> x += 1;

            }
        }
        if (tacticUpdateRequired) {
            tacticUpdateRequired = false;
            switch (currentState) {
                case SCATTER -> direction = getPreferredDirection(scatterX, scatterY, model.getGameField());
                case CHASE -> updateChaseTactic(model);
                case FRIGHTENED -> {
                    List<Direction> availableDirs = model.getGameField().getAvailableDirs(x, y, ID);
                    this.direction = availableDirs.get(rand.nextInt(availableDirs.size()));
                }
                case EATEN -> {
                }
            }

        }

    }

    protected Direction getPreferredDirection(int targetX, int targetY, GameField field) {
        List<Direction> availableDirs = field.getAvailableDirs(x, y, ID);

        int distance = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;
        Direction preferredDir = Direction.UP;

        for (Direction dir : availableDirs) {
            if (dir != Direction.getOppositeDir(this.direction)) {
                switch (dir) {
                    case LEFT -> distance = getCubedDistance(x - 1, y, targetX, targetY);
                    case RIGHT -> distance = getCubedDistance(x + 1, y, targetX, targetY);
                    case DOWN -> distance = getCubedDistance(x, y + 1, targetX, targetY);
                    case UP -> distance = getCubedDistance(x, y - 1, targetX, targetY);
                }
                if (distance < minDistance) {
                    minDistance = distance;
                    preferredDir = dir;
                }
            }
        }
        return preferredDir;
    }

    protected int getCubedDistance(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    abstract void updateChaseTactic(Model model);
}

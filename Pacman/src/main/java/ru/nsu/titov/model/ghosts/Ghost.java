package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.model.playfield.GameField;

import java.util.List;
import java.util.Random;

import static ru.nsu.titov.model.ghosts.GhostState.*;

abstract public class Ghost extends GameObject {
    protected int scatterX = 0;
    protected int scatterY = 0;

    protected int startX = 0;
    protected int startY = 0;
    protected int startVelocity = 0;
    protected final int leaveVelocity = 5;

    protected GhostState state = SCATTER;
    protected Random rand = new Random();

    protected boolean tacticUpdateRequired = false;

    public Ghost(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        startY = logicalY;
        startX = logicalX;
    }


    @Override
    public void onCollide(GameObject object, Model model) {
        if (object.getID() == ObjectId.PACMAN) {
            switch (state) {
                case FRIGHTENED -> {
                    state = EATEN;
                    doorsPassing = true;
                    velocity = leaveVelocity;
                    model.increaseScore(200);
                }
                case SCATTER, CHASE -> model.setPacmanEaten();
            }
        }
    }

    @Override
    public void tick(Model model) {
        switch (direction) {
            case LEFT, UP -> ticksPassed--;
            case DOWN, RIGHT -> ticksPassed++;
        }
        boolean tmp = false;
        if (ticksPassed <= -velocity || ticksPassed >= velocity) {
            tmp = true;
            ticksPassed = 0;
        }

        if (tmp) {
            tacticUpdateRequired = true;
            switch (direction) {
                case DOWN -> y += 1;
                case UP -> y -= 1;
                case LEFT -> x -= 1;
                case RIGHT -> x += 1;
            }
        }

        if (tacticUpdateRequired) {
            tacticUpdateRequired = false;
            switch (state) {
                case SCATTER -> direction = getPreferredDirection(scatterX, scatterY, model.getGameField());
                case CHASE -> updateChaseTactic(model);
                case FRIGHTENED -> {
                    List<Direction> availableDirs = model.getGameField().getAvailableDirs(x, y, ID);
                    availableDirs.remove(Direction.getOppositeDir(this.direction));
                    this.direction = availableDirs.get(rand.nextInt(availableDirs.size()));
                }
                case EATEN -> {
                    this.direction = getPreferredDirection(startX, startY, model.getGameField());
                    if (startX == x && startY == y) {
                        state = SCATTER;
                        doorsPassing = false;
                        velocity = startVelocity;
                    }
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
            if (availableDirs.size() != 1) {
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
            } else {
                preferredDir = availableDirs.get(0);
            }
        }
        return preferredDir;
    }

    protected int getCubedDistance(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    abstract void updateChaseTactic(Model model);

    public void setState(GhostState state) {
        if (this.state != EATEN) {
            this.state = state;
        }
    }

    @Override
    public void setDirection(Direction direction) {
        if (this.state != EATEN && this.state != FRIGHTENED) {
            this.direction = direction;
        }
    }

    public GhostState getState() {
        return state;
    }

    public void reset(){
        ticksPassed = 0;
        x = startX;
        y = startY;
        state = SCATTER;
    }
}

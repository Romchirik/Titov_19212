package ru.nsu.titov.model.pacman;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

import java.util.List;

public class Pacman extends GameObject {


    private int lives = 3;
    private boolean rageMode = false;

    private final double turnOverlap = 0.80;
    private Direction nextDir = Direction.UNDEFINED;
    private int delta = 1;

    public Pacman(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.PACMAN;
        direction = Direction.RIGHT;
    }

    public Pacman(int logicalX, int logicalY, int velocity) {
        super(logicalX, logicalY);
        ID = ObjectId.PACMAN;
        direction = Direction.RIGHT;
        this.velocity = velocity;
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
            switch (direction) {
                case DOWN -> y += 1;
                case UP -> y -= 1;
                case LEFT -> x -= 1;
                case RIGHT -> x += 1;
            }

            if (nextDir != Direction.UNDEFINED) {
                List<Direction> availableDirs = model.getGameField().getAvailableDirs(x, y, ID);
                availableDirs.forEach(dir -> {
                    if (nextDir == dir) {
                        this.direction = dir;
                    }
                });

            }
        }
    }

    @Override
    public void onCollide(GameObject object, Model model) {

    }

    @Override
    public void setDirection(Direction direction) {
//        if (this.direction == Direction.getOppositeDir(direction)) {
//            this.direction = direction;
//        }
//        nextDir = direction;

        this.direction = direction;

    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void reset() {
        ticksPassed = 0;
    }


}

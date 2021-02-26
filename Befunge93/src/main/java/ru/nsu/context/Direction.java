package ru.nsu;

import java.util.Random;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction randomDirection(){
        Direction[] values = Direction.values();
        int randIdx = new Random().nextInt(Direction.values().length);
        return values[randIdx];
    }
}

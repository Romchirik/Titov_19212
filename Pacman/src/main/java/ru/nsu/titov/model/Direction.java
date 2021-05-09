package ru.nsu.titov.model;

public enum Direction {
    UP, RIGHT, DOWN, LEFT, UNDEFINED;

    public static Direction getOppositeDir(Direction dir){
        return switch (dir){
            case UP -> DOWN;
            case RIGHT -> LEFT;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case UNDEFINED -> UNDEFINED;
        };
    }
}

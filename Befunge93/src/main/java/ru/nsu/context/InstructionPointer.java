package ru.nsu.context;


public class InstructionPointer {
    private int row = 0;
    private int column = 0;
    private Direction direction = Direction.RIGHT;

    public InstructionPointer() {

    }

    public InstructionPointer(int val1, int val2) {
        row = val1;
        column = val2;
    }

    public void setRow(int val) {
        row = val;
    }

    public void setColumn(int val) {
        column = val;
    }

    public void setRowColumn(int val1, int val2) {
        row = val1;
        column = val2;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void step() {
        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                column--;
                break;
            case RIGHT:
                column++;
                break;
            default:
        }
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return String.format("row: %d, column: %d", row, column);
    }
}

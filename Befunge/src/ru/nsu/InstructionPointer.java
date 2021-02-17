package ru.nsu;

import ru.nsu.exceptons.PtrOutOfBounds;
import ru.nsu.Direction;

import javax.lang.model.type.PrimitiveType;


public class InstructionPointer {
    int row = 0;
    int column = 0;
    Direction direction = Direction.UP;

    public InstructionPointer(int val1, int val2) {
        row = val1;
        column = val2;
    }

    public void setRow(int val) throws PtrOutOfBounds {
        row = val;
        if (row < 0 || row >= 80) {
            throw new PtrOutOfBounds("Ptr out of bounds!");
        }
    }

    public void setColumn(int val) throws PtrOutOfBounds {
        column = val;
        if (column < 0 || column >= 25) {
            throw new PtrOutOfBounds("Ptr out of bounds!");
        }
    }

    public void setPair(int val1, int val2) {
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

    public void step() throws PtrOutOfBounds {
        switch (direction) {
            case UP:
                row++;
                break;
            case DOWN:
                row--;
                break;
            case LEFT:
                column++;
                break;
            case RIGHT:
                column--;
                break;
            default:
        }
        if (row < 0 || row > 24 || column < 0 || column > 80) {
            throw new PtrOutOfBounds("Instruction pointer out of bounds!");
        }
    }

    @Override
    public String toString() {
        return new String("row: " + row + ";" + "column: " + column);
    }
}

package ru.nsu.playfield;


public class Playfield {
    static final int PLAYFIELD_WIDTH = 80;
    static final int PLAYFIELD_HEIGHT = 25;

    Character[][] field = new Character[PLAYFIELD_HEIGHT][PLAYFIELD_WIDTH];

    public void setInstruction(final int row, final int column, Character symbol) {
        field[column][row] = symbol;
    }

    public Character getInstruction(final int row, final int column) {
        return field[row][column];
    }
}


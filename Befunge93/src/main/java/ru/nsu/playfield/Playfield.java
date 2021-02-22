package ru.nsu.playfield;


public class Playfield {
    static final int PLAYFIELD_WIDTH = 80;
    static final int PLAYFIELD_HEIGHT = 25;

    private int current_row = 0;
    private int current_column = 0;


    Character[][] field = new Character[PLAYFIELD_HEIGHT][PLAYFIELD_WIDTH];


    public void setInstruction(final int row, final int column, Character symbol) {
        field[column][row] = symbol;
    }

    public Character getInstruction(final int row, final int column) {
        return field[row][column];
    }

    void addSymbol(Character symbol) {
        switch (symbol) {
            case '\n' -> {
                current_column = 0;
                current_row++;
            }
            case '\0' -> {
            }
            default -> {
                field[current_row][current_column] = symbol;
                current_column++;
            }
        }
    }
}


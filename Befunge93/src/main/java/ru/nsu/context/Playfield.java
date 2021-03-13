package ru.nsu.context;

public class Playfield {
    static final int PLAYFIELD_WIDTH = 80;
    static final int PLAYFIELD_HEIGHT = 25;

    private int current_row = 0;
    private int current_column = 0;


    Character[][] field = new Character[PLAYFIELD_HEIGHT][PLAYFIELD_WIDTH];

    public Playfield() {
        for (int i = 0; i < PLAYFIELD_HEIGHT; i++) {
            for (int j = 0; j < PLAYFIELD_WIDTH; j++) {
                field[i][j] = ' ';
            }
        }
    }

    public void setInstruction(int row, int column, Character symbol) {
        field[row % PLAYFIELD_HEIGHT][column % PLAYFIELD_WIDTH] = symbol;
    }

    public Character getInstruction(int row, int column) {
        int __row = row % PLAYFIELD_HEIGHT;
        int __column = column % PLAYFIELD_WIDTH;
        return field[__row < 0 ? __row + PLAYFIELD_HEIGHT : __row][__column < 0 ? __column + PLAYFIELD_WIDTH : __column];
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


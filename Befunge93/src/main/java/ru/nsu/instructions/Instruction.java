package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public interface Instruction {
    boolean exec(Context context, Character instruction) throws NoSuchElementException;
}

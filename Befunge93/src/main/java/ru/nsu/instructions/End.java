package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Terminates program
 */
public class End implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        return false;
    }
}

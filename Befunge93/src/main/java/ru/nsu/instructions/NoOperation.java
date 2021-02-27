package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class NoOperation implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        return true;
    }
}

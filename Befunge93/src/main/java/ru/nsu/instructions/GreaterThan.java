package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class GreaterThan implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        if (context.pop() < context.pop()) {
            context.push(1);
        } else {
            context.push(0);
        }
        return true;
    }
}

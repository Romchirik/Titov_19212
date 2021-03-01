package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class Subtraction implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.push(-context.pop() + context.pop());
        return true;
    }
}
package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class Addition implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException{
        context.stack.push(context.stack.pop() + context.stack.pop());
        return true;
    }
}
package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class Multiplication implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException{
        try {
            context.stack.push(context.stack.pop() * context.stack.pop());
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
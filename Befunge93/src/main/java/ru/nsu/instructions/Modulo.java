package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class Modulo implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        Integer a = context.pop();
        context.push(context.pop() % a);
        return true;
    }
}

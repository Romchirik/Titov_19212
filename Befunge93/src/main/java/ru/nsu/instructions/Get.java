package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class Get implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        Integer row = context.pop();
        Integer column = context.pop();

        context.push((int) context.getInstruction(row, column));
        return true;
    }
}

package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

//TODO протестировать говнокод
public class Put implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        Integer row = context.pop();
        Integer column = context.pop();

        context.setInstruction(row, column, (char) context.pop().intValue());
        return true;
    }
}

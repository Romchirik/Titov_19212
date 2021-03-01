package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class LogicalNot implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {

        if (context.pop() == 0) {
            context.push(1);
        } else {
            context.push(0);
        }
        return true;
    }
}

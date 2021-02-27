package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;


public class StartStopStringMode implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.step();
        Character charToPush = context.getCurrentInstruction();
        while (charToPush != '"') {
            context.stack.push((int) charToPush);
            context.step();
            charToPush = context.getCurrentInstruction();
        }
        return true;
    }
}

package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class PushNumber implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.push(instruction.hashCode() - 48);
        return true;
    }
}

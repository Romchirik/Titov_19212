package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class PopAsInteger implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        System.out.printf("%d ", context.pop());
        return true;
    }
}

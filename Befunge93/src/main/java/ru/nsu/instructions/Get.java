package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

//TODO Протестировать говнокод
public class Get implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.push((int) context.getInstruction(context.pop(),
                context.pop()));
        return true;
    }
}

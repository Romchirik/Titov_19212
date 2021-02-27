package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

//TODO протестировать говнокод
public class Put implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.playfield.setInstruction(context.stack.pop(),
                context.stack.pop(),
                (char) context.stack.pop().intValue());
        return true;
    }
}

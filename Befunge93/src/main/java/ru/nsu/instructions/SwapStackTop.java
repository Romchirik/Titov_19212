package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class SwapStackTop implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {

        Integer a = context.stack.pop();
        Integer b = context.stack.pop();

        context.stack.push(a);
        context.stack.push(b);

        return true;
    }
}

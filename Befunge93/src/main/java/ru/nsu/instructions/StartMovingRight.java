package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

public class StartMovingRight implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.instructionPointer.setDirection(Direction.RIGHT);
        return true;
    }
}

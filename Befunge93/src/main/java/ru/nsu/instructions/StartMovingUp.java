package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

public class StartMovingUp implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.setDirection(Direction.UP);
        return true;
    }
}

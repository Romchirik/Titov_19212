package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

public class PopAndMoveHorizontally implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {

        if (context.pop() == 0) {
            context.setDirection(Direction.RIGHT);
        } else {
            context.setDirection(Direction.LEFT);
        }
        return true;
    }
}

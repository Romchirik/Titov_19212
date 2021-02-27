package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

public class PopAndMoveVertical implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        if (context.stack.pop() == 0) {
            context.instructionPointer.setDirection(Direction.DOWN);
        } else {
            context.instructionPointer.setDirection(Direction.UP);
        }
        return true;
    }
}

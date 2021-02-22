package ru.nsu.instructions;

import ru.nsu.Direction;
import ru.nsu.InstructionPointer;

import java.util.Deque;

public class StartMovingRight implements Instruction {

    @Override
    public boolean exec(Deque<Integer> context, InstructionPointer instructionPointer) {
        instructionPointer.setDirection(Direction.RIGHT);
        return true;
    }
}

package ru.nsu.instructions;

import ru.nsu.Direction;
import ru.nsu.InstructionPointer;

import java.util.Deque;

public class StartMovingRandomDirection implements Instruction {
    @Override
    public boolean exec(Deque<Integer> context, InstructionPointer instructionPointer) {
        instructionPointer.setDirection(Direction.randomDirection());
        return true;
    }
}

package ru.nsu.instructions;

import ru.nsu.Direction;
import ru.nsu.Instruction;
import ru.nsu.InstructionPointer;

import java.util.Deque;

public class StartMovingDown implements Instruction {

    @Override
    public boolean exec(Deque<Integer> context, InstructionPointer instructionPointer) {
        instructionPointer.setDirection(Direction.DOWN);
        return true;
    }
}

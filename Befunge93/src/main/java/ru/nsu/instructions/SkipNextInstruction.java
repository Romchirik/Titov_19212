package ru.nsu.instructions;

import ru.nsu.InstructionPointer;

import java.util.Deque;

public class SkipNextInstruction implements Instruction {

    @Override
    public boolean exec(Deque<Integer> context, InstructionPointer instructionPointer) {
        return true;
    }
}

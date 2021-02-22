package ru.nsu.instructions;

import ru.nsu.InstructionPointer;

import java.util.Deque;

public interface Instruction {
    boolean exec(Deque<Integer> context, InstructionPointer instructionPointer);
}

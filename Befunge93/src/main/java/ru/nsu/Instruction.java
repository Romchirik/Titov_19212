package ru.nsu;

import java.util.Deque;

import ru.nsu.InstructionPointer;

public interface Instruction {
    boolean exec(Deque<Integer> context, InstructionPointer instructionPointer);
}

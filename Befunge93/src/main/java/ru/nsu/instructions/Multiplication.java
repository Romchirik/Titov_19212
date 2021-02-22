package ru.nsu.instructions;

import ru.nsu.InstructionPointer;

import java.util.Deque;
import java.util.NoSuchElementException;

public class Multiplication implements Instruction {
    @Override
    public boolean exec(Deque<Integer> context, InstructionPointer instructionPointer) {
        try {
            context.push(context.pop() * context.pop());
        } catch (NoSuchElementException e) {

            return false;
        }
        return true;
    }
}
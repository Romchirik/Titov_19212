package ru.nsu.instructions;

import ru.nsu.ErrorPrinter;
import ru.nsu.Instruction;
import ru.nsu.InstructionPointer;

import java.util.Deque;
import java.util.NoSuchElementException;

public class Addition implements Instruction {

    @Override
    public boolean exec(Deque<Integer> context, InstructionPointer instructionPointer) {
        try {
            context.push(context.pop() + context.pop());
        } catch (NoSuchElementException e) {
            ErrorPrinter.emptyStack(context, instructionPointer, '+');
            return false;
        }


        return true;
    }
}
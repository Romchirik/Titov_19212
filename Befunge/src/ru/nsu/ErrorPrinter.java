package ru.nsu;

import java.util.Deque;

public class ErrorPrinter {

    private ErrorPrinter() {
    }

    static public void unknownError(Deque<Integer> context, InstructionPointer instructionPointer, Character instruction) {
        printStackState(context);
        System.err.println("Err: unknown error");
        printInstructionPtrState(instructionPointer, instruction);
    }

    static public void stackOverflow(Deque<Integer> context, InstructionPointer instructionPointer, Character instruction) {
        System.err.println("Stack state:");
        while (!context.isEmpty()) {
            System.err.println(context.pop());
        }
        System.err.println("Err: stack overflow");
        printInstructionPtrState(instructionPointer, instruction);
    }

    static public void emptyStack(Deque<Integer> context, InstructionPointer instructionPointer, Character instruction) {
        System.err.println("Err: pop() on empty stack");
        printInstructionPtrState(instructionPointer, instruction);
    }

    static public void pointerOutOfBounds(Deque<Integer> context, InstructionPointer instructionPointer, Character instruction) {
        printStackState(context);
        System.err.println("Err: instruction pointer out of bounds");
        printInstructionPtrState(instructionPointer, instruction);

    }

    static void printStackState(Deque<Integer> context) {
        System.err.println("Stack state:");
        while (!context.isEmpty()) {
            System.err.println(context.pop());
        }
    }

    static void printInstructionPtrState(InstructionPointer instructionPointer, Character instruction) {
        System.err.println("Instruction: " + instruction.toString());
        System.err.println("Instruction pointer: " + instructionPointer.toString());
    }

}

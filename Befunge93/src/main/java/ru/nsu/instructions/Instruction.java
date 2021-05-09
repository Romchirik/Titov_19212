package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public interface Instruction {
    /**
     * Executes the instruction.
     *
     * @param context     execution context, contains instruction pointer, playfield and stack data.
     * @param instruction character value of current symbol for number instruction
     * @throws NoSuchElementException thrown if any of instruction trying to pop value from empty stack
     */
    boolean exec(Context context, Character instruction) throws NoSuchElementException;
}

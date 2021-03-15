package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AskCharPush implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.getOutput().println("Character needed:");
        Scanner charInput = new Scanner(context.getInput());
        Integer inputChar = (int) charInput.next().charAt(0);
        context.push(inputChar);
        return true;
    }
}

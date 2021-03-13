package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;
import java.util.Scanner;

//TODO доделать класс
public class AskCharPush implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.getOutput().println("Character needed:");
        Scanner intInput = new Scanner(context.getInput());
        int inputChar = intInput.nextByte();
        context.push(inputChar);
        return true;
    }
}

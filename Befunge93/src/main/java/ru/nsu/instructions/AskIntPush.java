package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class AskIntPush implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.getOutput().println("Integer needed:");
        Scanner intInput = new Scanner(context.getInput());
        Integer inputInt = intInput.nextInt();
        context.push(inputInt);
        return true;
    }
}

package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;
import java.util.Scanner;


//TODO доделать класс
public class AskIntPush implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.getOutput().println("Integer needed:");
        Scanner intInput = new Scanner(context.getInput());
        int inputInt = intInput.nextInt();
        context.push(inputInt);
        return true;
    }
}

package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class Modulo implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException{
        try {
            Integer a = context.stack.pop();
            context.stack.push(context.stack.pop() % a);
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}

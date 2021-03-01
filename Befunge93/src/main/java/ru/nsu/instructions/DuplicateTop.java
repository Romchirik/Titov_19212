package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class DuplicateTop implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException{
        try {
            context.push(context.getStackTop());
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}

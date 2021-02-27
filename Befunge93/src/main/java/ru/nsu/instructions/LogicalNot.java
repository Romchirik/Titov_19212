package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

public class LogicalNot implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException{
        try {
            if(context.stack.pop() == 0){
                context.stack.push(1);
            } else {
                context.stack.push(0);
            }
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}

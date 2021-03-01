package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

public class Division implements Instruction {
    static final Logger logger = Logger.getLogger(Division.class);
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException{
        try {
            Integer a = context.pop();
            context.push(context.pop() / a);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Конец программы, после исполнения инструкции выполнение прогрпммы завершается
 */
public class End implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        return false;
    }
}

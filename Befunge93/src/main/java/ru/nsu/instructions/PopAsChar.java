package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Pops a value from stack and output is as char
 * <table>
 * <tr>
 * <th>stack before</th>
 * <th>stack after</th>
 * </tr>
 * <tr>
 * <td>val</td>
 * <td></td>
 * </tr>
 * </table>
 */
public class PopAsChar implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        //TODO переделать на настраиваемые потоки
        System.out.printf("%c", context.pop());
        return true;
    }
}

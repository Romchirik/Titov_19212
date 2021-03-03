package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;


/**
 * Multiplies 2 values from stack top
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val1, val2</td>
 *     <td>val1 * val2</td>
 * </tr>
 * </table>
 */
public class Multiplication implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.push(context.pop() * context.pop());
        return true;
    }
}
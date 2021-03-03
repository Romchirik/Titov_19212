package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Popes compares 2 values from stack top. Pushes 1 if val1 > val2, 0 otherwise
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val1, val2</td>
 *     <td>1 if val1 > val2, 0 otherwise</td>
 * </tr>
 * </table>
 */
public class GreaterThan implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        if (context.pop() < context.pop()) {
            context.push(1);
        } else {
            context.push(0);
        }
        return true;
    }
}

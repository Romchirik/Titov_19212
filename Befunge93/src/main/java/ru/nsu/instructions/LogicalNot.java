package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;


/**
 * Push 0 if value non-zero, 1 otherwise
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>value</td>
 *     <td>0 if value non-zero, 1 otherwise</td>
 * </tr>
 * </table>
 */
public class LogicalNot implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {

        if (context.pop() == 0) {
            context.push(1);
        } else {
            context.push(0);
        }
        return true;
    }
}

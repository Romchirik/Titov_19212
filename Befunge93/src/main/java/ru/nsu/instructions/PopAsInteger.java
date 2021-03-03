package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Pops a value fros stack and output is as integer
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val</td>
 *     <td></td>
 * </tr>
 * </table>
 */
public class PopAsInteger implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        System.out.printf("%d ", context.pop());
        return true;
    }
}

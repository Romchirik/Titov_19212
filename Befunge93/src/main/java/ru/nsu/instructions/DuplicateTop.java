package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Duplicates integer from stack top
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val</td>
 *     <td>val, val</td>
 * </tr>
 * </table>
 */
public class DuplicateTop implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        try {
            context.push(context.getStackTop());
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}

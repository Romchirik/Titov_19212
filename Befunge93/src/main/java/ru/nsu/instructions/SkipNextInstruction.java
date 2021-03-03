package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Skips next instruction to execute
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td></td>
 *     <td></td>
 * </tr>
 * </table>
 */
public class SkipNextInstruction implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.setSkipTrue();
        return true;
    }
}

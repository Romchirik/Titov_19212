package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;


/**
 * Pushes number from playfield in stack. {@code Character instruction} needed
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
public class PushNumber implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.push(instruction.hashCode() - 48);
        return true;
    }
}

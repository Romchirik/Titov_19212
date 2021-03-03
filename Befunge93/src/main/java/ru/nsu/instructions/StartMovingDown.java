package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

/**
 * Changes moving direction of instruction pointer to right
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
public class StartMovingDown implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.setDirection(Direction.DOWN);
        return true;
    }
}

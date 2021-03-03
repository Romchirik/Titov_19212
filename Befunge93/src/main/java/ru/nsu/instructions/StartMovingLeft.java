package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

/**
 * Changes moving direction of instruction pointer to left
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
public class StartMovingLeft implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.setDirection(Direction.LEFT);
        return true;
    }
}

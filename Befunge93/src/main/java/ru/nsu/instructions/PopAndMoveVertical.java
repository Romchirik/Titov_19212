package ru.nsu.instructions;

import ru.nsu.context.Context;
import ru.nsu.context.Direction;

import java.util.NoSuchElementException;

/**
 * Moves down if val == 0, up otherwise. Pops value from stack top
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
public class PopAndMoveVertical implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        if (context.pop() == 0) {
            context.setDirection(Direction.DOWN);
        } else {
            context.setDirection(Direction.UP);
        }
        return true;
    }
}

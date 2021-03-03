package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Starts string mode. In this mode instruction pushes all values between "" in stack as chars
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td></td>
 *     <td>values from playfield between ""</td>
 * </tr>
 * </table>
 */
public class StartStopStringMode implements Instruction {

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.step();
        Character charToPush = context.getCurrentInstruction();
        while (charToPush != '"') {
            context.push((int) charToPush);
            context.step();
            charToPush = context.getCurrentInstruction();
        }
        return true;
    }
}

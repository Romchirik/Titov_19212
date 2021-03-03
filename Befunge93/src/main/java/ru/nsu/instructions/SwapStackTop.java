package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Swaps 2 values from stack top
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val1, val2</td>
 *     <td>val2, val1</td>
 * </tr>
 * </table>
 */
public class SwapStackTop implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {

        Integer a = context.pop();
        Integer b = context.pop();

        context.push(a);
        context.push(b);

        return true;
    }
}

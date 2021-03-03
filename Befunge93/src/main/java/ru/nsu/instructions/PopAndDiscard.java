package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Discards value fro stack top
 * <table class="striped">
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
public class PopAndDiscard implements Instruction{
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        context.pop();
        return true;
    }
}

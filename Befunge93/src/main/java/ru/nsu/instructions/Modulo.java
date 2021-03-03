package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;


/**
 * Count the remainder of a division of 2 integers from stack top
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val1, val2</td>
 *     <td>val1 mod val2</td>
 * </tr>
 * </table>
 */
public class Modulo implements Instruction {
    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        Integer a = context.pop();
        context.push(context.pop() % a);
        return true;
    }
}

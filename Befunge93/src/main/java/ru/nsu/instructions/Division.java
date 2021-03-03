package ru.nsu.instructions;

import ru.nsu.context.Context;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

/**
 * Divides 2 integers from stack top. Only integer division allowed
 * <table>
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val1, val2</td>
 *     <td>val1 / val2</td>
 * </tr>
 * </table>
 */
public class Division implements Instruction {
    static final Logger logger = Logger.getLogger(Division.class);

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        try {
            Integer a = context.pop();
            context.push(context.pop() / a);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
package ru.nsu.instructions;

import org.apache.log4j.Logger;
import ru.nsu.context.Context;

import java.util.NoSuchElementException;

/**
 * Adds 2 integers from stack top
 * <table class="striped">
 * <tr>
 *     <th>stack before</th>
 *     <th>stack after</th>
 * </tr>
 * <tr>
 *     <td>val1, val2</td>
 *     <td>val1 + val2</td>
 * </tr>
 * </table>
 */
public class Addition implements Instruction {

    static final Logger logger = Logger.getLogger(Instruction.class);

    @Override
    public boolean exec(Context context, Character instruction) throws NoSuchElementException {
        Integer a = context.pop();
        Integer b = context.pop();

        logger.trace(String.format("%d + %d = %d\n", a, b, a + b));
        context.push(a + b);
        return true;
    }
}
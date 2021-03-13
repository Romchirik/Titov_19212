package ru.nsu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.context.Context;
import ru.nsu.context.Direction;

public class ContextTest {
    static Context context;

    @BeforeAll
    static void init() {
        context = new Context();
    }

    @Test
    public void instructionPointerTest() {
        Assertions.assertEquals(context.getDirection(), Direction.RIGHT);
        context.step();
        Assertions.assertEquals(context.getColumn(), 1);
        Assertions.assertEquals(context.getRow(), 0);

        context.setDirection(Direction.DOWN);
        Assertions.assertEquals(context.getDirection(), Direction.DOWN);
        context.step();
        Assertions.assertEquals(context.getColumn(), 1);
        Assertions.assertEquals(context.getRow(), 1);

        context.setDirection(Direction.LEFT);
        Assertions.assertEquals(context.getDirection(), Direction.LEFT);
        context.step();
        Assertions.assertEquals(context.getColumn(), 0);
        Assertions.assertEquals(context.getRow(), 1);

        context.setDirection(Direction.UP);
        Assertions.assertEquals(context.getDirection(), Direction.UP);
        context.step();
        Assertions.assertEquals(context.getColumn(), 0);
        Assertions.assertEquals(context.getRow(), 0);
    }

    @Test
    public void playfieldTest() {

    }
}
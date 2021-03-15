package ru.nsu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.context.Context;
import ru.nsu.context.Direction;
import ru.nsu.instructions.*;

public class OperationsTest {

    static private Context context;

    @BeforeAll
    static void contextInit() {
        context = new Context();
    }

    @Test
    public void additionTest() {
        Instruction addition = new Addition();

        context.push(100);
        context.push(200);
        addition.exec(context, '+');

        Assertions.assertEquals(context.pop(), 300);
    }

    @Test
    public void divisionTest() {
        Instruction division = new Division();

        context.push(200);
        context.push(100);
        division.exec(context, '-');

        Assertions.assertEquals(context.pop(), 2);
    }

    @Test
    public void duplicateTopTest() {
        Instruction duplicate = new DuplicateTop();

        context.push(100);
        duplicate.exec(context, ':');

        Assertions.assertEquals(context.pop(), context.pop());
    }

    @Test
    public void putTest() {
        Instruction put = new Put();
        Integer row = 10;
        Integer column = 20;

        context.push((int) '&');
        context.push(column);
        context.push(row);
        put.exec(context, 'p');

        Assertions.assertEquals(context.getInstruction(row, column), '&');
    }

    @Test
    public void GreaterThanTest() {
        Instruction greaterThan = new GreaterThan();

        context.push(100);
        context.push(200);
        greaterThan.exec(context, '`');
        Assertions.assertEquals(context.pop(), 0);

        context.push(200);
        context.push(100);
        greaterThan.exec(context, '`');
        Assertions.assertEquals(context.pop(), 1);

        context.push(100);
        context.push(100);
        greaterThan.exec(context, '`');
        Assertions.assertEquals(context.pop(), 0);

    }

    @Test
    public void LogicalNotTest() {
        Instruction logicalNot = new LogicalNot();

        context.push(0);
        logicalNot.exec(context, '!');
        Assertions.assertEquals(context.pop(), 1);

        context.push(1);
        logicalNot.exec(context, '!');
        Assertions.assertEquals(context.pop(), 0);
    }

    @Test
    public void moduloTest() {
        Instruction modulo = new Modulo();

        context.push(200);
        context.push(100);
        modulo.exec(context, '%');

        Assertions.assertEquals(context.pop(), 0);
    }

    @Test
    public void MultiplicationTest() {
        Instruction modulo = new Modulo();

        context.push(200);
        context.push(100);
        modulo.exec(context, '*');
        Assertions.assertEquals(context.pop(), 0);
    }

    @Test
    public void popAndDiscardTest() {
        context.push(100);
        Assertions.assertDoesNotThrow(() -> {
            context.pop();
        });
    }

    @Test
    public void popAndMoveVerticalTest() {
        Instruction vertical = new PopAndMoveVertical();
        context.setInstruction(0, 0, '|');

        context.push(0);
        vertical.exec(context, '|');
        Assertions.assertEquals(context.getDirection(), Direction.DOWN);

        context.push(1);
        vertical.exec(context, '|');
        Assertions.assertEquals(context.getDirection(), Direction.UP);
    }

    @Test
    public void popAndMoveHorizontallyTest() {
        Instruction vertical = new PopAndMoveHorizontally();
        context.setInstruction(0, 0, '_');

        context.push(0);
        vertical.exec(context, '_');
        Assertions.assertEquals(context.getDirection(), Direction.RIGHT);

        context.push(1);
        vertical.exec(context, '_');
        Assertions.assertEquals(context.getDirection(), Direction.LEFT);
    }

    @Test
    public void pushNumberTest() {
        Instruction pushNumber = new PushNumber();

        pushNumber.exec(context, '0');
        pushNumber.exec(context, '1');
        pushNumber.exec(context, '2');
        pushNumber.exec(context, '3');
        pushNumber.exec(context, '4');
        pushNumber.exec(context, '5');
        pushNumber.exec(context, '6');
        pushNumber.exec(context, '7');
        pushNumber.exec(context, '8');
        pushNumber.exec(context, '9');

        Assertions.assertEquals(context.pop(), 9);
        Assertions.assertEquals(context.pop(), 8);
        Assertions.assertEquals(context.pop(), 7);
        Assertions.assertEquals(context.pop(), 6);
        Assertions.assertEquals(context.pop(), 5);
        Assertions.assertEquals(context.pop(), 4);
        Assertions.assertEquals(context.pop(), 3);
        Assertions.assertEquals(context.pop(), 2);
        Assertions.assertEquals(context.pop(), 1);
        Assertions.assertEquals(context.pop(), 0);
    }

    @Test
    public void getTest() {
        Integer row = 10;
        Integer column = 20;
        Instruction get = new Get();

        context.setInstruction(row, column, 'p');
        context.push(column);
        context.push(row);

        get.exec(context, 'g');
        Assertions.assertEquals(context.pop(), 'p');
    }

    @Test
    public void skipNextInstructionTest() {
        Instruction skip = new SkipNextInstruction();
        skip.exec(context, '#');
        context.step();

        Assertions.assertEquals(context.getColumn(), 2);
        Assertions.assertEquals(context.getRow(), 0);
    }

    @Test
    public void startMovingDownTest() {
        Instruction direction = new StartMovingDown();
        direction.exec(context, '>');
        Assertions.assertEquals(context.getDirection(), Direction.DOWN);
    }

    @Test
    public void startMovingLeftTest() {
        Instruction direction = new StartMovingLeft();
        direction.exec(context, '>');
        Assertions.assertEquals(context.getDirection(), Direction.LEFT);
    }

    @Test
    public void startMovingRightTest() {
        Instruction direction = new StartMovingRight();
        direction.exec(context, '>');
        Assertions.assertEquals(context.getDirection(), Direction.RIGHT);
    }

    @Test
    public void startMovingUpTest() {
        Instruction direction = new StartMovingUp();
        direction.exec(context, '>');
        Assertions.assertEquals(context.getDirection(), Direction.UP);
    }

    @Test
    public void subtractionTest() {
        Instruction subtraction = new Subtraction();

        context.push(100);
        context.push(200);
        subtraction.exec(context, '-');

        Assertions.assertEquals(context.pop(), -100);
    }

    @Test
    public void swapStackTopTest() {
        Instruction swapStackTop = new SwapStackTop();

        context.push(100);
        context.push(200);
        swapStackTop.exec(context, ' ');

        Assertions.assertEquals(context.pop(), 100);
        Assertions.assertEquals(context.pop(), 200);
    }
}

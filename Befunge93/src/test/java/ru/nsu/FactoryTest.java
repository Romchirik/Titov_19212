package ru.nsu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FactoryTest {
    static Factory factory;

    @BeforeAll
    static void init() {
        Assertions.assertDoesNotThrow(() -> {
            factory = new Factory();
        });
    }

    @Test
    public void instructionsLoadTest() {
        Assertions.assertNull(factory.createInstruction(';'));
        Assertions.assertDoesNotThrow(() -> {
            factory.createInstruction('+');
            factory.createInstruction('-');
            factory.createInstruction('*');
            factory.createInstruction('/');
            factory.createInstruction('%');
            factory.createInstruction('!');
            factory.createInstruction('`');
            factory.createInstruction('>');
            factory.createInstruction('<');
            factory.createInstruction('v');
            factory.createInstruction('^');
            factory.createInstruction('?');
            factory.createInstruction('_');
            factory.createInstruction('|');
            factory.createInstruction('"');
            factory.createInstruction(':');
            factory.createInstruction('\\');
            factory.createInstruction('$');
            factory.createInstruction('.');
            factory.createInstruction(',');
            factory.createInstruction('#');
            factory.createInstruction(' ');
            factory.createInstruction('g');
            factory.createInstruction('p');
            factory.createInstruction('&');
            factory.createInstruction('~');
            factory.createInstruction('@');
        });
    }


}

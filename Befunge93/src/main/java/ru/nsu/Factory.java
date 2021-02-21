package ru.nsu;

import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Factory {
    HashMap<Character, Class<?>> availableInstructions = new HashMap<>();
    private HashMap<Character, String> instructionClassAssociation = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Factory.class);

    public Factory(HashMap<Character, String> instructionClassAssociation) {
        logger.info("Loading properties...");


    }

    void loadInstruction(Character instructionName) throws ClassNotFoundException {
        Class<?> loadedClass = Class.forName(instructionClassAssociation.get(instructionName));
        availableInstructions.put(instructionName, loadedClass);
    }

    public Instruction createInstruction(Character instructionName) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        if (availableInstructions.get(instructionName) == null) {
            loadInstruction(instructionName);
        }
        return (Instruction) availableInstructions.get(instructionName).getDeclaredConstructor().newInstance();
    }
}
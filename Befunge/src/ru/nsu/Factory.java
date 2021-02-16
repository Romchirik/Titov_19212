package ru.nsu;

import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import ru.nsu.Instruction;

public class Factory {
    HashMap<Character, Class<?>> availableInstructions = new HashMap<Character, Class<?>>();
    final HashMap<Character, String> instructionClassAssociation;

    public Factory(HashMap<Character, String> instructionClassAssociation) {
        this.instructionClassAssociation = instructionClassAssociation;
    }

    void loadInstruction(Character instructionName) throws ClassNotFoundException {
        Class<?> loadedClass = Class.forName(instructionClassAssociation.get(instructionName));
        availableInstructions.put(instructionName, loadedClass);
    }

    Instruction createInstruction(Character instructionName) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        if(availableInstructions.get(instructionName) == null){
            loadInstruction(instructionName);
        }
        return (Instruction) availableInstructions.get(instructionName).getDeclaredConstructor().newInstance();
    }
}
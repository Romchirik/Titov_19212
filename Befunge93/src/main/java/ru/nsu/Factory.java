package ru.nsu;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Class;
import java.util.HashMap;
import java.util.Properties;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.exceptons.FactoryConfigurationException;
import ru.nsu.instructions.Instruction;


public class Factory {
    private final HashMap<Character, Class<?>> availableInstructions = new HashMap<>();
    private final HashMap<Character, String> instructionClassMatching = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Factory.class);

    public Factory() throws FactoryConfigurationException {
        Properties props = new Properties();
        logger.debug("Loading factory properties...");
        try (InputStream input = Factory.class.getResourceAsStream("/factoryConfig.properties")) {
            props.load(input);
            props.forEach((key, value) -> {
                instructionClassMatching.put(key.toString().charAt(0), value.toString());
                logger.debug(String.format("Loaded matching: instruction %s class: %s", key, value));
            });

        } catch (IOException e) {
            logger.error("IO Error occurred, can't load factory properties");
            throw new FactoryConfigurationException("IO Error occurred, can't load factory properties");
        }

    }

    void loadInstruction(Character instructionName) throws ClassNotFoundException {
        Class<?> loadedClass = Class.forName(instructionClassMatching.get(instructionName));
        availableInstructions.put(instructionName, loadedClass);
    }

    public Instruction createInstruction(Character instruction) {
        logger.debug(String.format("Creating instruction class: %s", instruction));
        try {
            if (availableInstructions.get(instruction) == null) {
                logger.debug(String.format("Can't create instruction '%s' trying to load instruction",
                        instruction));
                loadInstruction(instruction);
            }
            logger.debug("Successfully created: " + instruction);
            return (Instruction) availableInstructions.get(instruction).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error(String.format("Can't find class: %s", instructionClassMatching.get(instruction)));
            return null;
        }
    }
}
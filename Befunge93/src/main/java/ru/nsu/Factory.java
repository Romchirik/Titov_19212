package ru.nsu;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.exceptons.FactoryConfigurationException;
import ru.nsu.instructions.Instruction;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Factory class. Tales char value and creates associated instruction class.
 * All associations can be found in factoryConfig.properties. Uses lazy loading, instruction is loaded oly when required.
 */
public class Factory {
    private final HashMap<Character, Class<?>> availableInstructions = new HashMap<>();
    private final HashMap<Character, String> instructionClassMatching = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Factory.class);

    /**
     * Creates and configures factory from factoryConfig.properties (locked filepath, you're not allowed to change one)
     * Stores all loaded associations from .props in {@code availableInstructions}
     *
     * @throws FactoryConfigurationException thrown if factory unable to load factoryConfig.properties
     *                                       or error occurred while loading properties
     */
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

    /**
     * Creates new instruction using instructionClassMatching.
     * If instruction class wasn't loaded before, invokes {@code loadInstruction} to load instruction. Returns {@code null} if any error occurs
     *
     * @param instruction Character value of instruction
     * @return null if unable to create instruction, Instance of {@code Instruction} otherwise
     */
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
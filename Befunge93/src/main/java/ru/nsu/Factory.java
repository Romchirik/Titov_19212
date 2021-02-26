import java.io.FileInputStream;
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
    private final HashMap<Character, String> instructionClassAssociation = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Factory.class);

    public Factory() throws FactoryConfigurationException {
        Properties props = new Properties();
        logger.info("Loading factory properties...");
        try (InputStream input = new FileInputStream("factoryConfig.properties")) {
            props.load(input);
            props.entrySet().forEach((item) -> {
                instructionClassAssociation.put(
                        item.getKey().toString().charAt(0),
                        item.getValue().toString());
            });

        } catch (IOException e) {
            logger.error("IO Error occurred, can't load factory properties");
            throw new FactoryConfigurationException("IO Error occurred, can't load factory properties");
        }
    }

    void loadInstruction(Character instructionName) throws ClassNotFoundException {
        Class<?> loadedClass = Class.forName(instructionClassAssociation.get(instructionName));
        availableInstructions.put(instructionName, loadedClass);
    }

    public Instruction createInstruction(Character instruction) {
        try {
            if (availableInstructions.get(instruction) == null) {
                loadInstruction(instruction);
            }
            return (Instruction) availableInstructions.get(instruction).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error("Fuck java exceptions, some error occurred while creating class: " +
                    instructionClassAssociation.get(instruction));
            return null;
        }
    }
}
package nsu.titov.messages;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageFactory {

    private static final Logger logger = Logger.getLogger(MessageFactory.class);
    Map<Byte, Class<?>> messages = new HashMap<>();

    public MessageFactory() {
        Properties props = new Properties();

        try (InputStream input = getClass().getResourceAsStream("factory.properties")) {
            props.load(input);
            props.forEach((key, value) -> {
                try {
                    messages.put(Byte.valueOf(key.toString()), Class.forName(value.toString()));
                } catch (ClassNotFoundException e) {
                    logger.error(String.format("Unable to locate class %s", value));
                }
            });
        } catch (IOException e) {
            logger.error("Unable to locate factory.properties");
        }
    }

    public Message createMessage(byte[] payload) {
        try {
            return (Message) messages.get(payload[0]).getDeclaredConstructor(byte[].class).newInstance(payload);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("Unable to locate constructor matching byte[]");
            return null;
        }
    }
}

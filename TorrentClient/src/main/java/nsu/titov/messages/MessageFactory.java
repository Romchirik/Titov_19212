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


    private static MessageFactory instance = null;

    private MessageFactory() {
        Properties props = new Properties();

        try (InputStream input = MessageFactory.class.getResourceAsStream("/factory.properties")) {
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

    private Message createMessagePrivate(byte[] payload) {
        try {
            return (Message) messages.get(payload[0]).getDeclaredConstructor(byte[].class).newInstance(payload);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("Unable to locate constructor matching byte[]");
            return null;
        }
    }

    private static void checkInstance() {
        if (instance == null) {
            instance = new MessageFactory();
        }
    }

    public static Message createMessage(byte[] payload) {
        if (instance == null) {
            instance = new MessageFactory();
        }
        return instance.createMessagePrivate(payload);
    }

    public static Message createMessage(MessageId id) {
        return switch (id) {
            case CHOKE -> new ChokeMessage();
            case UNCHOKE -> new UnchokeMessage();
            case INTERESTED -> new InterestedMessage();
            case UNINTERESTED -> new UninterestedMessage();
            case HAVE -> new HaveMessage();
            case BITFIELD -> new BitfieldMessage();
            case REQUEST -> new RequestMessage();
            case PIECE -> new PieceMessage();
            case CANCEL -> new CancelMessage();
        };
    }

}

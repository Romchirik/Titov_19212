package nsu.titov.network_simple;

import nsu.titov.Settings;
import nsu.titov.messages.Info;
import nsu.titov.messages.Message;
import nsu.titov.messages.MessageFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionHandler {

    public static final Logger logger = Logger.getLogger(ConnectionHandler.class);
    private SocketChannel connection;
    private ByteBuffer buffer = ByteBuffer.allocate(Settings.MAX_MESSAGE_SIZE);

    private int expectedLength = -1;

    Queue<Message> messages = new ArrayDeque<>();

    public ConnectionHandler(SocketChannel connection) {
        this.connection = connection;
    }


    public boolean update() throws IOException {
        connection.read(buffer);
        Message tmp = nextMessage();
        while (tmp != null) {
            messages.offer(tmp);
            tmp = nextMessage();
        }
        return true;
    }

    public boolean ifIncoming() {
        return messages.size() > 0;
    }

    public Message getMessage() {
        return messages.poll();
    }

    public void putMessage(Message message) throws IOException {
        connection.write(ByteBuffer.wrap(message.getBytes()));
    }

    private Message nextMessage() {

        Message message = null;
        buffer.flip();

        if (expectedLength < 0 && buffer.hasRemaining()) {
            expectedLength = buffer.getInt();
        }

        if (buffer.limit() - buffer.position() >= expectedLength && expectedLength != -1) {
            if (expectedLength < Info.HEADER_SIZE) {
                logger.error("Invalid message length received, dropping connection");
                //we should drop connection here
                return null;
            }

            byte[] data = new byte[expectedLength];
            buffer.get(data, 0, expectedLength);
            message = MessageFactory.createMessage(data);
            expectedLength = -1;
        }

        buffer.compact();
        return message;
    }
}

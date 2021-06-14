package nsu.titov.network;

import nsu.titov.messages.Info;
import nsu.titov.messages.Message;
import nsu.titov.messages.MessageFactory;
import nsu.titov.peer.Settings;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Queue;

public class MessageReader {

    public int id;
    boolean handshakeAccepted = false;
    public static final Logger logger = Logger.getLogger(MessageReader.class);
    private ByteBuffer buffer = ByteBuffer.allocate(Settings.MAX_MESSAGE_SIZE * 2);

    private int expectedLength = -1;

    Queue<Message> messages = new ArrayDeque<>();

    public MessageReader(int id){
        this.id = id;
    }
    public void update(SocketChannel channel) throws IOException {
        channel.read(buffer);
        Message tmp = nextMessage();
        while (tmp != null) {
            messages.offer(tmp);
            tmp = nextMessage();
        }
    }

    public boolean ifIncoming() {
        return messages.size() > 0;
    }

    public Message getMessage() {
        return messages.poll();
    }

    public void sendMessage(Message message, SocketChannel channel) throws IOException {
        channel.write(ByteBuffer.wrap(message.getBytes()));
    }

    private Message nextMessage() {

        Message message = null;
        buffer.flip();

        if (expectedLength < 0 && buffer.hasRemaining()) {
            expectedLength = buffer.getInt() - 4;
        }

        if (buffer.limit() - buffer.position() >= expectedLength && expectedLength != -1) {
            byte[] data = new byte[expectedLength];
            buffer.get(data, 0, expectedLength);
            message = MessageFactory.createMessage(data);

            expectedLength = -1;
        }

        buffer.compact();

        return message;
    }
}

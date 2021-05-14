package nsu.titov;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Handshake {


    private final static int PROTOCOL_NAME_SHIFT = 1;
    private final static int PROTOCOL_NAME_SIZE = 19;
    private final static int RESERVED_SHIFT = 20;
    private final static int RESERVED_SIZE = 8;
    private final static int HASH_SHIFT = 28;
    private final static int HASH_SIZE = 20;
    private final static int PEER_ID_SHIFT = 48;
    private final static int PEER_ID_SIZE = 48;

    public final static int TOTAL_SIZE = PROTOCOL_NAME_SIZE + RESERVED_SIZE + HASH_SIZE + PEER_ID_SIZE;
    private byte[] handshakeBody = new byte[TOTAL_SIZE];

    private void setProtocol() {

    }

    public Handshake setName(String name) {

        byte length = (byte) (name.length() > PROTOCOL_NAME_SIZE ? PROTOCOL_NAME_SIZE : name.length());
        handshakeBody[0] = length;
        System.arraycopy(name.getBytes(StandardCharsets.UTF_8), 0, handshakeBody, PROTOCOL_NAME_SHIFT, length);
        return this;
    }

    public Handshake setReserved(byte[] reserved) {
        if (reserved == null) {
            return this;
        }
        if (reserved.length < RESERVED_SIZE) {
            return this;
        }

        System.arraycopy(reserved, 0, handshakeBody, RESERVED_SHIFT, RESERVED_SIZE);
        return this;
    }

    public static Handshake createHandshake() {
        return new Handshake();
    }

    public static Handshake createHandshake(byte[] handshakeBody) {
        Handshake tmp = new Handshake();
        System.arraycopy(handshakeBody, 0, tmp.handshakeBody, 0, TOTAL_SIZE);
        return tmp;
    }

    public byte[] getBody() {
        return handshakeBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Handshake that = (Handshake) o;
        return Arrays.equals(handshakeBody, that.handshakeBody);
    }


}

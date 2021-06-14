package nsu.titov.messages;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Handshake {

    private static final String DEFAULT_PROTOCOL_NAME = "BitTorrent protocol";

    private final static int PROTOCOL_NAME_OFFSET = 1;
    private final static int PROTOCOL_NAME_SIZE = 19;
    private final static int RESERVED_OFFSET = 20;
    private final static int RESERVED_SIZE = 8;
    private final static int HASH_OFFSET = 28;
    private final static int HASH_SIZE = 20;
    private final static int PEER_ID_OFFSET = 48;
    private final static int PEER_ID_SIZE = 48;

    public final static int TOTAL_SIZE = PROTOCOL_NAME_SIZE + RESERVED_SIZE + HASH_SIZE + PEER_ID_SIZE;
    private byte[] payload = new byte[TOTAL_SIZE];

    private Handshake() {
        payload[0] = (byte) PROTOCOL_NAME_SIZE;
        System.arraycopy(DEFAULT_PROTOCOL_NAME.getBytes(StandardCharsets.UTF_8), 0, payload, PROTOCOL_NAME_OFFSET, PROTOCOL_NAME_SIZE);
    }

    public Handshake setProtocolName(String name) {
        byte length = (byte) (name.length() > PROTOCOL_NAME_SIZE ? PROTOCOL_NAME_SIZE : name.length());
        System.arraycopy(name.getBytes(StandardCharsets.UTF_8), 0, payload, PROTOCOL_NAME_OFFSET, length);
        return this;
    }

    public Handshake setReserved(byte[] reserved) {
        if (reserved.length < RESERVED_SIZE) {
            throw new IllegalArgumentException("Reserved bytes too short, 8 required");
        }
        System.arraycopy(reserved, 0, payload, RESERVED_OFFSET, RESERVED_SIZE);
        return this;
    }

    public synchronized static Handshake createHandshake() {
        return new Handshake();
    }

    public static Handshake createHandshake(byte[] template) {
        Handshake tmp = new Handshake();
        if (template.length < TOTAL_SIZE) {
            throw new IllegalArgumentException("Reserved bytes too short, 8 required");
        }
        System.arraycopy(template, 0, tmp.payload, 0, TOTAL_SIZE);
        return tmp;
    }

    public byte[] getBytes() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Handshake that = (Handshake) o;
        return Arrays.equals(payload, that.payload);
    }


}

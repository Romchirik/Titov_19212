package nsu.titov.messages;

public enum MessageId {
    CHOKE, UNCHOKE, INTERESTED, UNINTERESTED, HAVE, BITFIELD, REQUEST, PIECE, CANCEL;

    public static byte idToByte(MessageId id) {
        return switch (id) {
            case CHOKE -> (byte) 0x00;
            case UNCHOKE -> (byte) 0x01;
            case INTERESTED -> (byte) 0x02;
            case UNINTERESTED -> (byte) 0x03;
            case HAVE -> (byte) 0x04;
            case BITFIELD -> (byte) 0x05;
            case REQUEST -> (byte) 0x06;
            case PIECE -> (byte) 0x07;
            case CANCEL -> (byte) 0x08;
        };
    }

    public static MessageId idToByte(byte id) {
        return switch (id) {
            case 0x00 -> CHOKE;
            case 0x01 -> UNCHOKE;
            case 0x02 -> INTERESTED;
            case 0x03 -> UNINTERESTED;
            case 0x04 -> HAVE;
            case 0x05 -> BITFIELD;
            case 0x06 -> REQUEST;
            case 0x07 -> PIECE;
            case 0x08 -> CANCEL;
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }
}
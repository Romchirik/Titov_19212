package nsu.titov.messages;

/* This class represents a message default message structure of BitTorrent protocol
 * All child classes represents different types of messages such as choke, bitfield etc and
 * should implement default constructor with 1 parameter:
 * int (represent total message size),
 */
abstract public class Message {
    protected int payloadSize = 0;
    protected MessageId id;
    byte[] payload;

    public MessageId getId() {
        return id;
    }

    @Deprecated
    public byte[] getBody() {
        return payload;
    }
}

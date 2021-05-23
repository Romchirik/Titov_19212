package nsu.titov.network;

import nsu.titov.messages.Message;



public interface Connection {
    void bind();

    boolean ifMessage();

    Message getMessage();

    void drop();


}

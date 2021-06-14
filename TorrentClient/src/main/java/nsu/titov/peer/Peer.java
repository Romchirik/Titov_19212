package nsu.titov.peer;


import nsu.titov.logic.Client;
import nsu.titov.logic.Server;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;


public class Peer implements Runnable {
    PeerInfo info;

    private static final Logger logger = Logger.getLogger(Peer.class);

    public Peer(PeerInfo info) throws IOException {
        this.info = info;
    }

    @Override
    public void run() {
        Runnable thread;
        if(info.seedingAllowed){
            try {
                thread = new Server(info, new File(info.metaData.name));
            } catch (IOException e) {
                logger.error("Unable to start server");
                return;
            }
        } else {
            try {
                thread = new Client(info);
            } catch (IOException e) {
                logger.error("Unable to start client");
                return;
            }
        }

        thread.run();
    }

}

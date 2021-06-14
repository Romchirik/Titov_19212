package nsu.titov.peer;


import nsu.titov.metainfo.MetaData;
import nsu.titov.metainfo.MetaDataLoader;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public final class PeerStarter implements Runnable {
    private static final Logger logger = Logger.getLogger(PeerStarter.class);

    private List<InetSocketAddress> peersAddresses;
    private InetSocketAddress ip;
    private File saveDir;
    private String peerId;
    private boolean seedingAllowed = false;
    private MetaData metadata;
    private int port = Settings.DEFAULT_PORT;

    private PeerStarter() {
        ip = new InetSocketAddress(Settings.DEFAULT_PORT);
        peerId = ip.toString();
    }

    public PeerStarter setTorrent(File torrentFile) throws IOException {
        if (torrentFile == null) {
            logger.warn("No .torrent file given");
            throw new IllegalArgumentException("No .torrent file given");
        }
        metadata = MetaDataLoader.decode(torrentFile);
        if (metadata == null) {
            logger.warn("Unable to load .torrent file");
            throw new IllegalArgumentException("Unable to load .torrent file");
        }
        return this;
    }

    public PeerStarter allowSeedingForced(boolean seedingAllowed) {
        this.seedingAllowed = seedingAllowed;
        return this;
    }

    public PeerStarter setSaveDir(File saveDir) {
        if (saveDir == null) return this;
        this.saveDir = saveDir;
        return this;
    }

    public PeerStarter setPeerId(String peerId) {
        if (peerId == null) return this;
        this.peerId = peerId;
        return this;
    }

    public PeerStarter setPeers(List<InetSocketAddress> peersAddresses) {
        if (peersAddresses == null) return this;
        this.peersAddresses = peersAddresses;
        return this;
    }

    public PeerStarter setPort(int port) {
        if(port == 0) return this;
        this.port = port;
        return this;
    }

    public static PeerStarter createPeer() {
        return new PeerStarter();
    }

    private PeerInfo init() {

        if (ip == null) {
            ip = new InetSocketAddress(port);
        }

        if (saveDir == null) {
            saveDir = new File("./");
        }

        if (peerId == null) {
            peerId = ip.toString();
        }

        if (metadata == null) {
            logger.warn("No .torrent file given");
            System.exit(0);
        }

        PeerInfo peerInfoTmp = new PeerInfo();
        peerInfoTmp.availablePeers = peersAddresses;
        peerInfoTmp.seedingAllowed = seedingAllowed;
        return peerInfoTmp;
    }

    private boolean findFile(String dir, String name) {
        File tmp = new File(new File(dir), name);
        return tmp.exists();
    }

    @Override
    public void run() {
        PeerInfo peerInfo = init();
        peerInfo.metaData = metadata;
        peerInfo.incomingPort = port;
        Peer peer;

        try {
            peer = new Peer(peerInfo);
            logger.info("Peer started");
            peer.run();
        } catch (IOException e) {
            logger.error("Unknown error while staring peer");
            System.exit(-1);
        }


    }
}

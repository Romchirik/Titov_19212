package nsu.titov.peer;

import com.turn.ttorrent.common.Torrent;
import nsu.titov.filesystem.FilesystemHandler;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class Peer implements Runnable {
    private static final Logger logger = Logger.getLogger(Peer.class);

    private List<InetSocketAddress> peersAddresses;
    private InetSocketAddress ip;
    private File saveDir;
    private String peerId;

    private Torrent metadata;
    FilesystemHandler filesystemHandler;

    private Peer() {
        ip = new InetSocketAddress(Settings.DEFAULT_PORT);
        peerId = ip.toString();
    }

    public Peer setTorrent(File torrentFile) throws IOException, NoSuchAlgorithmException {
        if (torrentFile == null) {
            logger.warn("No torrent file given");
            throw new IllegalArgumentException("No torrent file given");
        }
        metadata = Torrent.load(torrentFile);
        return this;
    }

    public Peer setSaveDir(File saveDir) {
        if (saveDir == null) return this;
        this.saveDir = saveDir;
        return this;
    }

    public Peer setPeerId(String peerId) {
        if (peerId == null) return this;
        this.peerId = peerId;
        return this;
    }

    public Peer setPeers(List<InetSocketAddress> peersAddresses) {
        if (peersAddresses == null) return this;
        this.peersAddresses = peersAddresses;
        return this;
    }

    public Peer setIp(String ip) {
        if (ip == null) return this;
        this.ip = new InetSocketAddress(ip, Settings.DEFAULT_PORT);
        return this;
    }

    public static Peer createPeer() {
        return new Peer();
    }

    private void init() {

    }


    @Override
    public void run() {
        init();

    }
}

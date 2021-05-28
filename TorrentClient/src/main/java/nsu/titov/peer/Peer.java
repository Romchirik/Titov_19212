package nsu.titov.peer;

import nsu.titov.Settings;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;

public class Peer implements Runnable {

    private List<InetSocketAddress> peersAddresses;
    private InetSocketAddress ip;
    private File saveDir;
    private String peerId;

    private Peer() {
        ip = new InetSocketAddress(Settings.DEFAULT_PORT);
        peerId = ip.toString();
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

    @Override
    public void run() {

    }
}

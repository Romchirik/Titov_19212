package nsu.titov.peer;

import nsu.titov.metainfo.MetaData;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.List;

public class PeerInfo {
    public boolean seedingAllowed;
    public URL trackerUrl;
    public List<InetSocketAddress> availablePeers;
    public MetaData metaData;
}

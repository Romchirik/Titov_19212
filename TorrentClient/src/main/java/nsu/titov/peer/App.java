package nsu.titov.peer;

import nsu.titov.converters.Ipv4Converter;
import picocli.CommandLine;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "bt-client", description = "Simple one-file BitTorrent client")
public class App implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "FILE", description = ".torrent file to work with")
    File torrentFile;

    @CommandLine.Option(names = {"-s", "--save"},
            description = "Save the torrent in directory save_dir (default: ./)",
            paramLabel = "save_dir")
    File saveDir;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "print this help screen")
    boolean help;

    @CommandLine.Option(names = {"-b", "--bind"},
            description = "bind to this port for incoming connections,\n" + "ports are selected automatically",
            paramLabel = "ip")
    String ip;

    @CommandLine.Option(names = {"-i", "--id"},
            description = "Set the node identifier to id, a hex string\n" +
                    "(default: computed based on ip and port)",
            paramLabel = "save_dir")
    String id;

    @CommandLine.Option(names = {"-p", "--port"}, converter = Ipv4Converter.class,
            description = "Instead of contacting the tracker for a peer list,\n" +
                    "use this peer instead, ip:port (ip or hostname)\n" +
                    "(include multiple -p for more than 1 peer)",
            paramLabel = "ip:port")
    List<InetSocketAddress> otherPeers;

    @Override
    public Integer call() throws Exception {
        Peer peer = Peer.createPeer()
                .setPeerId(id)
                .setIp(ip)
                .setPeers(otherPeers)
                .setSaveDir(saveDir)
                .setTorrent(torrentFile);
        peer.run();

        return 0;
    }

    public static void main(String... args) {
        System.exit(new CommandLine(new App()).setParameterExceptionHandler(new PrintExceptionMessageHandler()).execute(args));
    }


}

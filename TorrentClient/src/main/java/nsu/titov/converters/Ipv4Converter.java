package nsu.titov.converters;

import picocli.CommandLine;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public class Ipv4Converter implements CommandLine.ITypeConverter<InetSocketAddress> {
    @Override
    public InetSocketAddress convert(String value) throws Exception {
        List<String> addr = Arrays.asList(value.split(":"));
        return new InetSocketAddress(addr.get(0), Integer.parseInt(addr.get(1)));
    }
}

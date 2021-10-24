package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.FreeSpace;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.util.Base64;

import static org.transmission.client.examples.FreeSpaceMain.printFree;

public class TrClientBuilderProxyMain {
    public static void main(String[] args) throws TrException {
        Credentials credentials = Credentials.get();

        String proxyAuth = new String(Base64.getEncoder().encode("user:secret".getBytes()));

        TrClient client = TrClient.newBuilder()
                .rpcUri(credentials.rpcURI())
                .authenticate(credentials.getAuthentication())
                .httpClientTune(builder -> builder.proxy(ProxySelector.of(new InetSocketAddress("localhost", 8080))))
                .httpRequestTune(builder -> builder.header("Proxy-Authorization", "Basic " + proxyAuth))
                .useGzip()
                .strictJsonMapping()
                .build();

        FreeSpace fs = new FreeSpace("/downloads");
        FreeSpace free = client.freeSpace(fs);
        printFree(free);

        System.out.println(Printer.duration(client.getTiming().duration()));
    }
}

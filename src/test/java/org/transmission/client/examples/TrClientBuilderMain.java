package org.transmission.client.examples;

import org.transmission.client.Credentials;
import org.transmission.client.Printer;
import org.transmission.client.TrClient;
import org.transmission.client.TrException;
import org.transmission.client.args.FreeSpace;

import java.time.Duration;

import static org.transmission.client.examples.FreeSpaceMain.printFree;

public class TrClientBuilderMain {
    public static void main(String[] args) throws TrException {
        Credentials credentials = Credentials.get();

        TrClient client = TrClient.newBuilder()
                .rpcUri(credentials.rpcURI())
                .authenticate(credentials.getAuthentication())
                .httpClientTune(builder -> builder.connectTimeout(Duration.ofSeconds(10)))
                .useGzip()
                .strictJsonMapping()
                .build();

        FreeSpace fs = new FreeSpace("/downloads");
        FreeSpace free = client.freeSpace(fs);
        printFree(free);

        System.out.println(Printer.duration(client.getTiming().duration()));
    }
}

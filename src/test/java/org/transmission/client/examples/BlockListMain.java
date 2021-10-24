package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.BlockList;

public class BlockListMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        BlockList test = client.blocklistUpdate();
        Printer.println(test);

        System.out.println(client.getTiming());
    }
}

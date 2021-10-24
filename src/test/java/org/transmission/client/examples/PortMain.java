package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.PortTest;

public class PortMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        PortTest test = client.portTest();
        Printer.println(test);

        System.out.println(Printer.duration(client.getTiming().duration()));
    }
}

package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.SessionStats;

public class SessionStatsMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        SessionStats stats = client.sessionStats();
        Printer.println(stats);
        System.out.println(client.getTiming());
    }
}

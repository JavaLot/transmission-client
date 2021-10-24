package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.Session;
import org.transmission.client.args.SessionGet;

import java.util.List;

public class SessionGetMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        SessionGet sg = new SessionGet(List.of("download-dir"));
        Session session = client.sessionGet(/*sg*/);
        Printer.println(session);
        System.out.println(Printer.duration(client.getTiming().postDuration()));
        System.out.println(Printer.duration(client.getTiming().parseDuration()));
    }
}

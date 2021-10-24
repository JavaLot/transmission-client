package org.transmission.client.examples;

import org.transmission.client.*;

public class SessionCloseMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        client.sessionClose();
        TrTiming timing = client.getTiming();
        System.out.println("two posts: " + timing.isTwoPosts() + " session: " + timing.sessionId());
    }
}

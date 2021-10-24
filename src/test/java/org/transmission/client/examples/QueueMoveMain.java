package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.Identities;
import org.transmission.client.args.TorrentIds;

public class QueueMoveMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        TorrentIds ids = new TorrentIds(Identities.of(1));
        client.queueMoveTop(ids);
        client.queueMoveUp(ids);
        client.queueMoveBottom(ids);
        client.queueMoveDown(ids);
    }
}

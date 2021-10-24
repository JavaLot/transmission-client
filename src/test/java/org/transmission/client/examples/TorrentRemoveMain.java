package org.transmission.client.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.transmission.client.*;
import org.transmission.client.args.Identities;
import org.transmission.client.args.TorrentRemove;

import java.util.List;

public class TorrentRemoveMain {
    public static void main(String[] args) throws TrException, JsonProcessingException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        TorrentRemove remove = new TorrentRemove(Identities.of(1), true);

        client.torrentRemove(remove);
    }
}

package org.transmission.client.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.transmission.client.*;
import org.transmission.client.args.*;

import java.util.List;

public class TorrentGetRecentlyMain {
    public static void main(String[] args) throws JsonProcessingException, TrException {
//        System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        SessionStats stats = client.sessionStats();
        System.out.println("Torrents: " + stats.torrentCount());
        System.out.println("Download speed: " + ByteUnit.print(stats.downloadSpeed()) + "/s");
        System.out.println("Upload speed: " + ByteUnit.print(stats.uploadSpeed()) + "/s");

        var torrentGetRecently = new TorrentGet(FieldsSelector.getProperties(Torrent.class), Identities.ofRecently());
        Torrents res = client.torrentGet(torrentGetRecently);
        res.torrents().forEach(Printer::println);
        List<Integer> removed = res.removed();
        if(!removed.isEmpty()) {
            System.out.println("Removed: " + removed);
        }

        TrTiming timing = client.getTiming();
        Printer.println(timing);
    }
}

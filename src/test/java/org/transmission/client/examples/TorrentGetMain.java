package org.transmission.client.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.transmission.client.*;
import org.transmission.client.args.FieldsSelector;
import org.transmission.client.args.Torrent;
import org.transmission.client.args.Torrents;
import org.transmission.client.args.TorrentGet;
import org.transmission.client.jackson.JacksonProvider;

import java.util.List;

public class TorrentGetMain {
    public static void main(String[] args) throws JsonProcessingException, TrException {
//        System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        TorrentGet torrentGet = new TorrentGet(FieldsSelector.getProperties(Torrent.class));

        final ObjectMapper mapper = JacksonProvider.getMapper(true).enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(torrentGet));

        Torrents res = client.torrentGet(torrentGet);

        res.torrents().forEach(t -> {
                    Printer.println(t);
                    List<Torrent.Tracker> trackers = t.trackers();
                    List<Torrent.TrackerStat> stats = t.trackerStats();
                    trackers.forEach(Printer::println);
                    stats.forEach(Printer::println);
        });
        System.out.println(client.getTiming());
    }
}

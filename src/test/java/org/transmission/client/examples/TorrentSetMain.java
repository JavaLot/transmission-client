package org.transmission.client.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.transmission.client.Credentials;
import org.transmission.client.Printer;
import org.transmission.client.TrClient;
import org.transmission.client.TrException;
import org.transmission.client.args.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class TorrentSetMain {
    public static void main(String[] args) throws JsonProcessingException, TrException, MalformedURLException {
//        System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        TorrentSet arg1 = TorrentSetBuilder.builder()
                .ids(Identities.of(1))
                .trackerAdd(List.of(new URL("http://localhost:88888/announce")))
                .build();

        client.torrentSet(arg1);

        TorrentGet arg2 = new TorrentGet(FieldsSelector.getProperties(Torrent.class), 1);
        Torrents res2 = client.torrentGet(arg2);

        Torrent torrent1 = res2.torrents().get(0);
        List<Torrent.Tracker> trackers1 = torrent1.trackers();
        Optional<Torrent.Tracker> tracker1 = trackers1.stream().filter(tr -> tr.announce().toString().startsWith("http://localhost:")).findFirst();

        if(tracker1.isPresent()) {
            Printer.println(tracker1.get());

            TorrentSet arg3 = TorrentSetBuilder.builder()
                    .ids(Identities.of(1))
                    .trackerReplace(Couples.of(tracker1.get().id(), new URL("http://localhost:99999/announce")))
                    .build();

            client.torrentSet(arg3);
        }

        TorrentGet arg4 = new TorrentGet(FieldsSelector.getProperties(Torrent.class), 1);
        Torrents res4 = client.torrentGet(arg4);

        Torrent torrent2 = res4.torrents().get(0);
        List<Torrent.Tracker> trackers2 = torrent2.trackers();
        Optional<Torrent.Tracker> tracker2 = trackers2.stream().filter(tr -> tr.announce().toString().startsWith("http://localhost:")).findFirst();

        if(tracker2.isPresent()) {
            Printer.println(tracker2.get());

            TorrentSet arg5 = TorrentSetBuilder.builder()
                    .ids(Identities.of(1))
                    .trackerRemove(List.of(tracker2.get().id()))
                    .build();
            client.torrentSet(arg5);
        }
    }
}

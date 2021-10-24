package org.transmission.client.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.transmission.client.*;
import org.transmission.client.args.*;
import org.transmission.client.jackson.JacksonProvider;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class TorrentRenamePathMain {
    public static void main(String[] args) throws TrException, JsonProcessingException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        SessionGet sg = new SessionGet(FieldsSelector.getProperties(Session.class));
        Session session = client.sessionGet(sg);

        Path downloadDir = Path.of(session.downloadDir());
        System.out.println(downloadDir);

        TorrentGet torrentGet = new TorrentGet(List.of("downloadDir", "id", "name", "status"));
        Torrents res = client.torrentGet(torrentGet);
        List<Torrent> torrents = res.torrents();
        torrents = torrents.stream().filter(t -> t.name().contains("arcolinuxl")).collect(Collectors.toList());
        if(!torrents.isEmpty()) {
            Torrent torrent = torrents.get(0);
            Printer.println(torrent);
            String path = torrent.name();
            String name = "arcolinux.iso";
            TorrentRenamePath arg = new TorrentRenamePath(torrent.id(), path, name);
            TorrentRenamedPath resRename = client.torrentRenamePath(arg);
            Printer.println(resRename);
        }
    }
}

package org.transmission.client.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.transmission.client.*;
import org.transmission.client.args.*;
import org.transmission.client.jackson.JacksonProvider;

import java.io.File;

public class TorrentAddMain {
    public static void main(String[] args) throws TrException, JsonProcessingException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        SessionGet sg = new SessionGet(FieldsSelector.getProperties(Session.class));
        Session session = client.sessionGet(sg);
        Printer.println(session);
        System.out.println(client.getTiming());

        String dir = session.downloadDir();
        File file = new File("arcolinuxl-v21.09.11-x86_64.iso.torrent");
        MetaInfo metaInfo = new MetaInfo(file);

        if(metaInfo.isGood()) {
            TorrentAdd add = TorrentAddBuilder.builder()
                    .metainfo(metaInfo)
                    .downloadDir(dir + "/linux")
                    //.paused(Boolean.TRUE)
                    .build();

            final ObjectMapper mapper = JacksonProvider.getMapper(true).enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(mapper.writeValueAsString(add));

            TorrentAdded added = client.torrentAdd(add);
            Printer.println(added);
            System.out.println(client.getTiming());
        }
    }
}

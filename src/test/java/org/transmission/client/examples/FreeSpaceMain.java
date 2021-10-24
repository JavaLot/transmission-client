package org.transmission.client.examples;

import org.transmission.client.*;
import org.transmission.client.args.FreeSpace;

public class FreeSpaceMain {
    public static void main(String[] args) throws TrException {
        //System.setProperty("jdk.httpclient.HttpClient.log", "all");

        Credentials credentials = Credentials.get();
        TrClient client = new TrClient(credentials.rpcURI(), credentials.getAuthentication());

        FreeSpace fs = new FreeSpace("/downloads");
        FreeSpace free = client.freeSpace(fs);
        printFree(free);

        System.out.println(Printer.duration(client.getTiming().postDuration()));
    }

    public static void printFree(FreeSpace free) {
        System.out.printf("%-20s%s\n", free.path(), ByteUnit.print(free.sizeBytes()));
    }
}

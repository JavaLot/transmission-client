package org.transmission.client.args;

public record TorrentSetLocation(
        Identities ids,
        String location,
        Boolean move
) {
    public TorrentSetLocation(
            Identities ids,
            String location
    ) {
        this(ids, location, Boolean.FALSE);
    }
}

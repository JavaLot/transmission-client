package org.transmission.client.args;

import java.util.Collection;

public record TorrentGet(
        Collection<String> fields,
        Identities ids
) {
    public TorrentGet(Collection<String> fields) {
        this(fields, (Identities) null);
    }

    public TorrentGet(Collection<String> fields, int ... ids) {
        this(fields, Identities.of(ids));
    }
}

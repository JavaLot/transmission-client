package org.transmission.client.args;

public record TorrentRenamedPath(
        Integer id,
        String name,
        String path
) {
}

package org.transmission.client.args;

import java.util.List;

public record Torrents(
        List<Torrent> torrents,
        List<Integer> removed
) {
}

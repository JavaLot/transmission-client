package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TorrentAdded(
        @JsonProperty("torrent-added")
        Torrent added,
        @JsonProperty("torrent-duplicate")
        Torrent duplicate
) {
}

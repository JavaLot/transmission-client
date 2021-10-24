package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record TorrentRemove(
    Identities ids,
    @JsonProperty("delete-local-data")
    Boolean deleteLocalData
) {
}

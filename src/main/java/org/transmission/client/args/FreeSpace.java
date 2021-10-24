package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FreeSpace(
        String path,
        @JsonProperty("size-bytes")
        Long sizeBytes
) {
    public FreeSpace(String path) {
        this(path, 0L);
    }
}

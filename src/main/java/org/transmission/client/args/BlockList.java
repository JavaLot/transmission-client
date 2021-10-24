package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BlockList(
        @JsonProperty("blocklist-size")
        Integer blocklistSize
) {
}

package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PortTest(
        @JsonProperty("port-is-open")
        Boolean portIsOpen
) {
}

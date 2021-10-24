package org.transmission.client.args;

import java.util.Collection;

public record SessionGet(
        Collection<String> fields
) {
}

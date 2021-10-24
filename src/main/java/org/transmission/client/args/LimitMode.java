package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LimitMode {
    /* follow the global settings */
    GLOBAL(0),
    /* override the global settings, seeding until a certain idle time */
    SINGLE(1),
    /* override the global settings, seeding regardless of activity */
    UNLIMITED(2);

    private final int limit;

    LimitMode(int limit) {
        this.limit = limit;
    }

    @JsonValue
    public int getLimit() {
        return limit;
    }
}

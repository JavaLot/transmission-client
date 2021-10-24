package org.transmission.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Transmission rpc response.
 *
 * @param <V> response Value class
 */
record TrResponse<V>(
        String result,
        V arguments
) {
    public TrResponse(String result) {
        this(result, null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return "success".equals(result);
    }
}

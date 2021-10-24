package org.transmission.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Transmission rpc request.
 *
 * @param <P> request Parameters value class
 */
record TrRequest<P>(
        TrMethod method,
        P arguments
) {
    TrRequest(TrMethod method) {
        this(method, null);
    }
}

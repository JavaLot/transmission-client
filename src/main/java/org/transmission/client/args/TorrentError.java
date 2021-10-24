package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TorrentError {
        /** everything's fine */
        OK(0),
        /** when we announced to the tracker, we got a warning in the response */
        TRACKER_WARNING(1),
        /** when we announced to the tracker, we got an error in the response */
        TRACKER_ERROR(2),
        /** local trouble, such as disk full or permissions error */
        LOCAL_ERROR(3);
    private final int error;

    TorrentError(int error) {
        this.error = error;
    }

    @JsonValue
    public int getError() {
        return error;
    }
}

package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TrackerState {
        /* we won't (announce,scrape) this torrent to this tracker because
         * the torrent is stopped, or because of an error, or whatever */
        INACTIVE(0),
        /* we will (announce,scrape) this torrent to this tracker, and are
         * waiting for enough time to pass to satisfy the tracker's interval */
        WAITING(1),
        /* it's time to (announce,scrape) this torrent, and we're waiting on
         * a free slot to open up in the announce manager */
        QUEUED(2),
        /* we're (announcing,scraping) this torrent right now */
        ACTIVE(3);

    private final int state;

    TrackerState(int state) {
        this.state = state;
    }

    @JsonValue
    public int getState() {
        return state;
    }
}

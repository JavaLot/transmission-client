package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * status - a number between 0 and 6, where:
 | 0: Torrent is stopped                |
 | 1: Queued to check files             |
 | 2: Checking files                    |
 | 3: Queued to download                |
 | 4: Downloading                       |
 | 5: Queued to seed                    |
 | 6: Seeding                           |
 */
public enum TorrentStatus {
    STOPPED(0), QUEUED_CHECK(1), CHECKING(2), QUEUED_DOWNLOAD(3), DOWNLOADING(4), QUEUED_SEED(5), SEEDING(6);

    private final int status;

    TorrentStatus(int status) {
        this.status = status;
    }

    @JsonValue
    public int getStatus() {
        return status;
    }
}

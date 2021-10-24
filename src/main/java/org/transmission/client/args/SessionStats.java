package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SessionStats(
        Integer activeTorrentCount,
        Integer pausedTorrentCount,
        Integer torrentCount,
        Long downloadSpeed,
        Long uploadSpeed,
        @JsonProperty("cumulative-stats")
        Stats cumulativeStats,
        @JsonProperty("current-stats")
        Stats currentStats
) {
    public record Stats(
            Long uploadedBytes,
            Long downloadedBytes,
            Long filesAdded,
            Integer sessionCount,
            Long secondsActive
    ) {}
}

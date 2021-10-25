package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
@RecordBuilder.Options(inheritComponentAnnotations = false)
public record Session(
        @JsonProperty("alt-speed-down")
        Integer altSpeedDown,
        @JsonProperty("alt-speed-enabled")
        Boolean altSpeedEnabled,
        @JsonProperty("alt-speed-time-begin")
        Integer altSpeedTimeBegin,
        @JsonProperty("alt-speed-time-enabled")
        Boolean altSpeedTimeEnabled,
        @JsonProperty("alt-speed-time-end")
        Integer altSpeedTimeEnd,
        @JsonProperty("alt-speed-time-day")
        Integer altSpeedTimeDay,
        @JsonProperty("alt-speed-up")
        Integer altSpeedUp,
        @JsonProperty("blocklist-url")
        String blocklistUrl,
        @JsonProperty("blocklist-enabled")
        Boolean blocklistEnabled,
        @JsonProperty("blocklist-size")
        Integer blocklistSize,
        @JsonProperty("cache-size-mb")
        Integer cacheSizeMb,
        @JsonProperty("config-dir")
        String configDir,
        @JsonProperty("download-dir")
        String downloadDir,
        @JsonProperty("download-dir-free-space")
        Long downloadDirFreeSpace,
        @JsonProperty("download-queue-size")
        Integer downloadQueueSize,
        @JsonProperty("download-queue-enabled")
        Boolean downloadQueueEnabled,
        @JsonProperty("dht-enabled")
        Boolean dhtEnabled,
        String encryption,
        @JsonProperty("idle-seeding-limit")
        Integer idleSeedingLimit,
        @JsonProperty("idle-seeding-limit-enabled")
        Boolean idleSeedingLimitEnabled,
        @JsonProperty("incomplete-dir")
        String incompleteDir,
        @JsonProperty("incomplete-dir-enabled")
        Boolean incompleteDirEnabled,
        @JsonProperty("lpd-enabled")
        Boolean lpdEnabled,
        @JsonProperty("peer-limit-global")
        Integer peerLimitGlobal,
        @JsonProperty("peer-limit-per-torrent")
        Integer peerLimitPerTorrent,
        @JsonProperty("pex-enabled")
        Boolean pexEnabled,
        @JsonProperty("peer-port")
        Integer pearPort,
        @JsonProperty("peer-port-random-on-start")
        Boolean peerPortRandomOnStart,
        @JsonProperty("port-forwarding-enabled")
        Boolean portForwardingEnabled,
        @JsonProperty("queue-stalled-enabled")
        Boolean queueStalledEnabled,
        @JsonProperty("queue-stalled-minutes")
        Integer queueStalledMinutes,
        @JsonProperty("rename-partial-files")
        Boolean renamePartialFiles,
        @JsonProperty("rpc-version")
        Integer rpcVersion,
        @JsonProperty("rpc-version-minimum")
        Integer rpcVersionMin,
        @JsonProperty("script-torrent-done-filename")
        String scriptTorrentDoneFilename,
        @JsonProperty("script-torrent-done-enabled")
        Boolean scriptTorrentDoneEnabled,
        Double seedRatioLimit,
        Boolean seedRatioLimited,
        @JsonProperty("seed-queue-size")
        Integer seedQueueSize,
        @JsonProperty("seed-queue-enabled")
        Boolean seedQueueEnabled,
        @JsonProperty("speed-limit-down")
        Integer speedLimitDown,
        @JsonProperty("speed-limit-down-enabled")
        Boolean speedLimitDownEnabled,
        @JsonProperty("speed-limit-up")
        Integer speedLimitUp,
        @JsonProperty("speed-limit-up-enabled")
        Boolean speedLimitUpEnabled,
        @JsonProperty("start-added-torrents")
        Boolean startAddedTorrents,
        @JsonProperty("trash-original-torrent-files")
        Boolean trashOriginalTorrentFiles,
        Units units,
        @JsonProperty("utp-enabled")
        Boolean utpEnabled,
        String version
) {
    public record Units(
            @JsonProperty("speed-units")
            List<String> speedUnits,
            @JsonProperty("speed-bytes")
            Integer speedBytes,
            @JsonProperty("size-units")
            List<String> sizeUnits,
            @JsonProperty("size-bytes")
            Integer sizeBytes,
            @JsonProperty("memory-units")
            List<String> memoryUnits,
            @JsonProperty("memory-bytes")
            Integer memoryBytes
    ) {}
}

package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.BitSet;
import java.util.List;

public record Torrent(
        LocalDateTime activityDate,
        LocalDateTime addedDate,
        Byte bandwidthPriority,
        String comment,
        Long corruptEver,
        String creator,
        LocalDateTime dateCreated,
        Long desiredAvailable,
        LocalDateTime doneDate,
        String downloadDir,
        Long downloadedEver,
        Integer downloadLimit,
        Boolean downloadLimited,
        LocalDateTime editDate,
        TorrentError error,
        String errorString,
        Integer eta,
        Integer etaIdle,
        @JsonProperty("file-count")
        Integer fileCount,
        List<File> files,
        List<FileStat> fileStats,
        String hashString,
        Long haveUnchecked,
        Long haveValid,
        Boolean honorsSessionLimits,
        Integer id,
        Boolean isFinished,
        Boolean isPrivate,
        Boolean isStalled,
        List<String> labels,
        Long leftUntilDone,
        String magnetLink,
        Integer manualAnnounceTime,
        Integer maxConnectedPeers,
        Double metadataPercentComplete,
        String name,
        @JsonProperty("peer-limit")
        Integer peerLimit,
        List<Peer> peers,
        Integer peersConnected,
        PeersFrom peersFrom,
        Integer peersGettingFromUs,
        Integer peersSendingToUs,
        Double percentDone,
        BitSet pieces,
        Long pieceCount,
        Long pieceSize,
        List<Byte> priorities,
        @JsonProperty("primary-mime-type")
        String primaryMimeType,
        Integer queuePosition,
        Integer rateDownload,
        Integer rateUpload,
        Double recheckProgress,
        Long secondsDownloading,
        Long secondsSeeding,
        Integer seedIdleLimit,
        LimitMode seedIdleMode,
        Double seedRatioLimit,
        LimitMode seedRatioMode,
        Long sizeWhenDone,
        LocalDateTime startDate,
        TorrentStatus status,
        List<Tracker> trackers,
        List<TrackerStat> trackerStats,
        Long totalSize,
        String torrentFile,
        Long uploadedEver,
        Integer uploadLimit,
        Boolean uploadLimited,
        Double uploadRatio,
        List<Boolean> wanted,
        List<String> webseeds,
        Integer webseedsSendingToUs
) {
    public static final List<String> METADATA = List.of(
            "id",
            "addedDate",
            "file-count",
            "name",
            "primary-mime-type",
            "totalSize"
    );
    public static final List<String> STATS = List.of(
            "id",
            "error",
            "errorString",
            "eta",
            "isFinished",
            "isStalled",
            "leftUntilDone",
            "metadataPercentComplete",
            "peersConnected",
            "peersGettingFromUs",
            "peersSendingToUs",
            "percentDone",
            "queuePosition",
            "rateDownload",
            "rateUpload",
            "recheckProgress",
            "seedRatioMode",
            "seedRatioLimit",
            "sizeWhenDone",
            "status",
            "trackers",
            "downloadDir",
            "uploadedEver",
            "uploadRatio",
            "webseedsSendingToUs"
    );
    public static final List<String> INFO_EXTRA = List.of(
            "id",
            "comment",
            "creator",
            "dateCreated",
            "files",
            "hashString",
            "isPrivate",
            "pieceCount",
            "pieceSize"
    );
    public static final List<String> STATS_EXTRA = List.of(
            "id",
            "activityDate",
            "corruptEver",
            "desiredAvailable",
            "downloadedEver",
            "fileStats",
            "haveUnchecked",
            "haveValid",
            "peers",
            "startDate",
            "trackerStats"
    );

    public record File(
            Long bytesCompleted,
            Long length,
            String name
    ) {
    }

    public record FileStat(
            Long bytesCompleted,
            Boolean wanted,
            Byte priority
    ) {
    }

    public record Peer(
            String address,
            String clientName,
            Boolean clientIsChoked,
            Boolean clientIsInterested,
            String flagStr,
            Boolean isDownloadingFrom,
            Boolean isEncrypted,
            Boolean isIncoming,
            Boolean isUploadingTo,
            Boolean isUTP,
            Boolean peerIsChoked,
            Boolean peerIsInterested,
            Integer port,
            Double progress,
            Integer rateToClient,
            Integer rateToPeer
    ) {
    }

    public record PeersFrom(
            Integer fromCache,
            Integer fromDht,
            Integer fromIncoming,
            Integer fromLpd,
            Integer fromLtep,
            Integer fromPex,
            Integer fromTracker
    ) {
    }

    public record Tracker(
            URL announce,
            Integer id,
            URL scrape,
            Integer tier
    ) {
    }

    public record TrackerStat(
            URL announce,
            TrackerState announceState,
            Integer downloadCount,
            Boolean hasAnnounced,
            Boolean hasScraped,
            String host,
            Integer id,
            Boolean isBackup,
            Integer lastAnnouncePeerCount,
            String lastAnnounceResult,
            LocalDateTime lastAnnounceStartTime,
            Boolean lastAnnounceSucceeded,
            LocalDateTime lastAnnounceTime,
            Boolean lastAnnounceTimedOut,
            String lastScrapeResult,
            LocalDateTime lastScrapeStartTime,
            Boolean lastScrapeSucceeded,
            LocalDateTime lastScrapeTime,
            Boolean lastScrapeTimedOut,
            Integer leecherCount,
            LocalDateTime nextAnnounceTime,
            LocalDateTime nextScrapeTime,
            URL scrape,
            TrackerState scrapeState,
            Integer seederCount,
            Integer tier
    ) {
    }
}

package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Collection;

@RecordBuilder
public record TorrentAdd(
        String cookies,
        @JsonProperty("download-dir")
        String downloadDir,
        String filename,
        MetaInfo metainfo,
        Boolean paused,
        @JsonProperty("peer-limit")
        Integer peerLimit,
        Byte bandwidthPriority,
        @JsonProperty("files-wanted")
        Collection<Integer> filesWanted,
        @JsonProperty("files-unwanted")
        Collection<Integer> filesUnwanted,
        @JsonProperty("priority-high")
        Collection<Integer> priorityHigh,
        @JsonProperty("priority-low")
        Collection<Integer> priorityLow,
        @JsonProperty("priority-normal")
        Collection<Integer> priorityNormal
) {
}

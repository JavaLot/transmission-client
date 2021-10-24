package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.net.URL;
import java.util.Collection;

@RecordBuilder
public record TorrentSet(
        Byte bandwidthPriority,
        Integer downloadLimit,
        Boolean downloadLimited,
        @JsonProperty("files-wanted")
        Collection<Integer> filesWanted,
        @JsonProperty("files-unwanted")
        Collection<Integer> filesUnwanted,
        Boolean honorsSessionLimits,
        Identities ids,
        Collection<String> labels,
        String location,
        @JsonProperty("peer-limit")
        Integer peerLimit,
        @JsonProperty("priority-high")
        Collection<Integer> priorityHigh,
        @JsonProperty("priority-low")
        Collection<Integer> priorityLow,
        @JsonProperty("priority-normal")
        Collection<Integer> priorityNormal,
        Integer queuePosition,
        Integer seedIdleLimit,
        LimitMode seedIdleMode,
        Double seedRatioLimit,
        LimitMode seedRatioMode,
        Collection<URL> trackerAdd,
        Collection<Integer> trackerRemove,
        Couples<Integer, URL> trackerReplace,
        Integer uploadLimit,
        Boolean uploadLimited
) {
}

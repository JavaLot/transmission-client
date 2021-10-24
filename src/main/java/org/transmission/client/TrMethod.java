package org.transmission.client;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.transmission.client.args.*;

enum TrMethod {
    TORRENT_START("torrent-start", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_START_NOW("torrent-start-now", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_STOP("torrent-stop", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_VERIFY("torrent-verify", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_REANNOUNCE("torrent-reannounce", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_SET("torrent-set", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_GET("torrent-get", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Torrents>>() {})),
    TORRENT_ADD("torrent-add", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<TorrentAdded>>() {})),
    TORRENT_REMOVE("torrent-remove", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_SET_LOCATION("torrent-set-location", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    TORRENT_RENAME_PATH("torrent-rename-path", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<TorrentRenamedPath>>() {})),
    SESSION_SET("session-set", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    SESSION_GET("session-get", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Session>>() {})),
    SESSION_STATS("session-stats", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<SessionStats>>() {})),
    BLOCKLIST_UPDATE("blocklist-update", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<BlockList>>() {})),
    PORT_TEST("port-test", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<PortTest>>() {})),
    SESSION_CLOSE("session-close", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    QUEUE_MOVE_TOP("queue-move-top", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    QUEUE_MOVE_UP("queue-move-up", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    QUEUE_MOVE_DOWN("queue-move-down", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    QUEUE_MOVE_BOTTOM("queue-move-bottom", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<Void>>() {})),
    FREE_SPACE("free-space", TypeFactory.defaultInstance().constructType(new TypeReference<TrResponse<FreeSpace>>() {}))
    ;

    private final String methodName;
    private final JavaType javaType;

    TrMethod(String methodName, JavaType javaType) {
        this.methodName = methodName;
        this.javaType = javaType;
    }

    @JsonValue
    public String getMethodName() {
        return methodName;
    }

    public JavaType getJavaType() {
        return javaType;
    }
}

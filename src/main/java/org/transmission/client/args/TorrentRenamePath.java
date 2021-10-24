package org.transmission.client.args;

public record TorrentRenamePath(
    Identities ids,
    String path,
    String name
) {
    public TorrentRenamePath{
        if(ids.size() != 1) throw new IllegalArgumentException("must only be 1 torrent");
    }

    public TorrentRenamePath(Integer id, String path, String name) {
        this(Identities.of(id), path, name);
    }
}

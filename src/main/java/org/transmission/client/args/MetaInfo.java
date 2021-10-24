package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class MetaInfo {
    private final byte[] metainfo;
    private final IOException exception;

    public MetaInfo(File file) {
        byte[] metainfo;
        IOException exception;

        try {
            metainfo = Files.readAllBytes(file.toPath());
            exception = null;
        } catch (IOException e) {
            metainfo = null;
            exception = e;
        }

        this.metainfo = metainfo;
        this.exception = exception;
    }

    public byte[] getMetainfo() {
        return metainfo;
    }

    public IOException getException() {
        return exception;
    }

    public boolean isGood() {
        return exception == null;
    }

    @JsonValue
    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(metainfo);
    }
}

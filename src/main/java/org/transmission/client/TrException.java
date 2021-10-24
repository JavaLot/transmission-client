package org.transmission.client;

public class TrException extends Exception {
    public TrException(Exception cause) {
        super(cause);
    }

    public TrException(String message) {
        super(message);
    }
}

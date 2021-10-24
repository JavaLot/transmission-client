package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collection;

public interface Identities {
    @JsonValue
    Object identities();
    int size();

    static Identities ofRecently() {
        return new RecentlyIdentity();
    }

    static Identities of(int ... ids) {
        return new CollectionIdentities(ids);
    }

    static Identities ofString(String ... ids) {
        return new CollectionIdentities(ids);
    }

    static Identities ofCollection(Collection<?> ids) {
        return new CollectionIdentities(ids);
    }
}

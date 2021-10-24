package org.transmission.client.args;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class CollectionIdentities implements Identities {
    private final Collection<Object> identities = new LinkedList<>();


    public CollectionIdentities() {
    }

    public CollectionIdentities(int ... ids) {
        for(int id: ids) {
            identities.add(id);
        }
    }

    public CollectionIdentities(String ... ids) {
        identities.addAll(Arrays.asList(ids));
    }

    public CollectionIdentities(Collection<?> ids) {
        ids.forEach(id -> {
            if(id instanceof Integer || id instanceof String) {
                identities.add(id);
            } else {
                throw new IllegalArgumentException("Object must be Integer or String");
            }
        });
    }

    @Override
    public Object identities() {
        return identities;
    }

    @Override
    public int size() {
        return identities.size();
    }

    public void add(int id) {
        identities.add(id);
    }

    public void add(Integer id) {
        identities.add(id);
    }

    public void add(String id) {
        identities.add(id);
    }
}

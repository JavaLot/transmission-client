package org.transmission.client.args;

class RecentlyIdentity implements Identities {
    @Override
    public Object identities() {
        return "recently-active";
    }

    @Override
    public int size() {
        return 1;
    }
}

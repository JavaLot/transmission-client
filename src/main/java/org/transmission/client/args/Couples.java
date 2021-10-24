package org.transmission.client.args;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Couples<K, V> {
    private final List<Object> couples = new LinkedList<>();

    public void add(K key, V value) {
        couples.add(key);
        couples.add(value);
    }

    @JsonValue
    public Collection<Object> getCouples() {
        return Collections.unmodifiableList(couples);
    }

    @Override
    public String toString() {
        return Arrays.toString(couples.toArray());
    }

    public static <K, V> Couples<K, V> of(K k1, V v1) {
        Couples<K, V> result = new Couples<>();
        result.add(k1, v1);
        return result;
    }

    public static <K, V> Couples<K, V> of(K k1, V v1, K k2, V v2) {
        Couples<K, V> result = new Couples<>();
        result.add(k1, v1);
        result.add(k2, v2);
        return result;
    }

    public static <K, V> Couples<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        Couples<K, V> result = new Couples<>();
        result.add(k1, v1);
        result.add(k2, v2);
        result.add(k3, v3);
        return result;
    }

    public static <K, V> Couples<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Couples<K, V> result = new Couples<>();
        result.add(k1, v1);
        result.add(k2, v2);
        result.add(k3, v3);
        result.add(k4, v4);
        return result;
    }
}

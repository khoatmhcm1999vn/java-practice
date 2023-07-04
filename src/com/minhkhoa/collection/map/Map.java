package com.minhkhoa.collection.map;

import com.minhkhoa.collection.Iterable;

public interface Map<K, V> extends Iterable<K> {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    void putAll(Map<K, V> otherMap);

    void clear();
}

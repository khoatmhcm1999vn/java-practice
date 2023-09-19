package com.mk.examples.collection;

public interface Collection<T> extends Iterable<T> {
    boolean insert(T inValue);

    boolean contain(T inValue);

    boolean isEmpty();

    void clear();

    void insertAll(Collection<T> collection);

    int size();
}

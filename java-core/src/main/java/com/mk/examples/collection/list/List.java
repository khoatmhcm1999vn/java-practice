package com.mk.examples.collection.list;

import com.mk.examples.collection.Collection;

public interface List<T> extends Collection<T> {
    T getAt(int index);

    boolean removeAt(int index);

    void reverse();

    boolean insertAt(T value, int index);

    int indexOf(T value);
}

package com.mk.examples.refactor.collection;

import java.util.Objects;
import java.util.function.Predicate;

public interface Collection<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    boolean remove(Object o);

    boolean containsAll(java.util.Collection<?> c);

    boolean addAll(java.util.Collection<? extends E> c);

    boolean removeAll(java.util.Collection<?> c);

    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    boolean retainAll(java.util.Collection<?> c);

    void clear();

    boolean equals(Object o);

    int hashCode();

}

package com.mk.examples.refactor.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;

/**
 * Separate chaining fashion HashMap implementation
 *
 * @param <K> The type of key
 * @param <V> The type of value
 */
public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size = 0;

    private int capacity;

    private float loadFactor;

    private int threshold;

    private LinkedList<Entry<K, V>>[] table;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity, float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity value is negative and not valid");
        }
        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("Load factor value is not valid");
        }
        this.loadFactor = loadFactor;
        this.capacity = Integer.max(DEFAULT_CAPACITY, capacity);
        this.threshold = (int) (this.capacity * this.loadFactor);
        this.table = new LinkedList[this.capacity];
    }

    /**
     * This method is exactly the Hash Function
     *
     * @param hashcode int The hashcode of the key
     * @return
     */
    private int hashcodeToIndex(int hashcode) {
        int index = hashcode % this.capacity;
        return 0;
    }

    /**
     * This method is the generating method which provide an iterator instance for the current hashmap
     *
     * @return Iterator<K>
     */
    @Override
    public Iterator<K> iterator() {
        final int elementCount = size();

        return new Iterator() {
            int index = 0;
            Iterator<Entry<K, V>> bucketIterator = table[0] == null ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                if (elementCount != size()) {
                    throw new ConcurrentModificationException("The table has been change it's state to empty while the client iterates elements !");
                }

                if (bucketIterator == null || !bucketIterator.hasNext()) {
                    while (++index < capacity) {
                        if (table[index] != null || !table[index].isEmpty()) {
                            bucketIterator = table[index].iterator();
                            break;
                        }
                    }
                }
                return index < capacity;
            }

            @Override
            public K next() {
                return bucketIterator.next().getKey();
            }
        };
    }

    /**
     * This method is used to return the current size
     *
     * @return boolean
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method is used to return the boolean value that stand for this hashmap is empty or not
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * This method is used to return the boolean value that stand for this hashmap contains a specific key or not
     *
     * @param seekKey K The key we want to seek
     * @return boolean
     */
    @Override
    public boolean containsKey(K seekKey) {
        int index = this.hashcodeToIndex(seekKey.hashCode());
        LinkedList<Entry<K, V>> entryLinkedList = this.table[index];
        if (Objects.isNull(entryLinkedList)) {
            return false;
        }
        Iterator<Entry<K, V>> iterator = entryLinkedList.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> currentEntry = iterator.next();
            if (currentEntry.getKey().equals(seekKey)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to return the boolean value that stand for this hashmap contains a specific value or not
     *
     * @param seekValue V The value we want to seek
     * @return boolean
     */
    @Override
    public boolean containsValue(V seekValue) {
        Iterator<? extends K> iterator = this.iterator();
        if (iterator.hasNext()) {
            K key = iterator.next();
            V value = this.get(key);
            if (value.equals(seekValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to return the value of an entry which is stored in the current hashmap through a specific key
     *
     * @param key K The key related to the entry which we want to seek
     * @return The value of the entry which sticks with the key (input)
     */
    @Override
    public V get(K key) {
        int index = this.hashcodeToIndex(key.hashCode());
        LinkedList<Entry<K, V>> entryLinkedList = this.table[index];
        if (Objects.isNull(entryLinkedList)) {
            return null;
        }
        Iterator<Entry<K, V>> iterator = entryLinkedList.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> currentEntry = iterator.next();
            if (currentEntry.getKey().equals(key)) {
                return currentEntry.getValue();
            }
        }
        return null;
    }

    /**
     * This method is used to put a new entry into the current hashmap
     *
     * @param key   K The key of the new entry
     * @param value K The value of the new entry
     * @return The value of the new entry
     */
    @Override
    public V put(K key, V value) {
        int index = this.hashcodeToIndex(key.hashCode());
        LinkedList<Entry<K, V>> entryLinkedList = this.table[index];
        if (Objects.isNull(entryLinkedList)) {
            this.table[index] = new LinkedList<>();
            entryLinkedList = this.table[index];
        }

        Entry<K, V> existedEntry = null;
        Iterator<Entry<K, V>> iterator = entryLinkedList.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> currentEntry = iterator.next();
            if (currentEntry.getKey().equals(key)) {
                existedEntry = currentEntry;
                break;
            }
        }

        if (Objects.isNull(existedEntry)) {
            entryLinkedList.insert(new Entry<>(key, value));
            if (++this.size > this.threshold) {
                this.handleResizingTable();
            }
            return value;
        }
        existedEntry.setValue(value);
        return existedEntry.getValue();
    }

    /**
     * This private method is used internally to resize the current hashmap when
     * it reached to our specified scale quantity
     */
    private void handleResizingTable() {
        this.capacity *= 2;
        this.threshold = (int) (this.capacity * this.loadFactor);
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[this.capacity];

        for (int i = 0; i < this.table.length; i++) {
            LinkedList<Entry<K, V>> entryLinkedList = this.table[i];
            if (Objects.isNull(entryLinkedList)) {
                continue;
            }
            Iterator<Entry<K, V>> iterator = entryLinkedList.iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                int index = this.hashcodeToIndex(entry.getHash());
                LinkedList<Entry<K, V>> entryLinkedList1 = newTable[index];
                if (Objects.isNull(entryLinkedList1)) {
                    newTable[index] = new LinkedList<>();
                }
                newTable[index].insert(entry);
            }
            table[i].clear();
            table[i] = null;
        }
        this.table = newTable;
    }

    /**
     * This method is used to handle removing an entry which stick with a key
     *
     * @param key K The key
     * @return V The value has been removed if completed or null if not completed
     */
    @Override
    public V remove(K key) {
        int index = this.hashcodeToIndex(key.hashCode());
        LinkedList<Entry<K, V>> entryLinkedList = this.table[index];
        if (Objects.isNull(entryLinkedList)) {
            return null;
        }

        Iterator<Entry<K, V>> iterator = entryLinkedList.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> currentEntry = iterator.next();
            if (currentEntry.getKey().equals(key)) {
                entryLinkedList.remove(currentEntry);
                --this.size;
                return currentEntry.getValue();
            }
        }
        return null;
    }

    /**
     * This method is used to handle adding another Map instance to the current hashmap
     *
     * @param otherMap
     */
    @Override
    public void putAll(Map<K, V> otherMap) {
        Iterator<? extends K> iterator = otherMap.iterator();
        if (iterator.hasNext()) {
            K key = iterator.next();
            V value = otherMap.get(key);
            this.put(key, value);
        }
    }

    /**
     * Clear all entries in the current Hashmap
     */
    @Override
    public void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
    }

    /**
     * This is the override toString method to represent the current hashmap as a String
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < table.length; i++) {
            LinkedList<Entry<K, V>> linkedList = table[i];
            if (linkedList == null || linkedList.isEmpty()) {
                continue;
            }
            Iterator<Entry<K, V>> iterator = linkedList.iterator();
            do {
                Entry<K, V> node = iterator.next();
                sb.append(node.toString()).append(", ");
            } while (iterator.hasNext());
        }
        sb.append("}");
        return sb.toString();
    }
}

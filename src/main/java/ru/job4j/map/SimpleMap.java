package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vladimir Likhachev
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int index = indexFor(key.hashCode());
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private int indexFor(int hash) {
        int index = hash & (capacity - 1);
        return index;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tmp = table;
        MapEntry<K, V>[] table = new MapEntry[capacity];
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != null) {
                table[indexFor(tmp[i].key.hashCode())] = tmp[i];
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
                return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        modCount = count;
        return new Iterator<>() {
            int head = 0;
            final int expectedModcount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModcount != modCount) {
                    throw new ConcurrentModificationException();
                }
                    while (head < capacity - 1  && table[head] == null) {
                        head++;
                }
                return table[head] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[head++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}

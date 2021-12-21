package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        container[container.]
    }

    @Override
    public T set(int index, T newValue) {
        return newValue;
    }

    @Override
    public T remove(int index) {
        return container[index];
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return container[0];
            }

        };
    }
}
package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        container[modCount] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T tmp = null;
        if (index < 0 && index > modCount) {
            tmp = container[index];
            container[index] = newValue;
        }
        return tmp;
    }

    @Override
    public T remove(int index) {
        T tmp = container[index];
            return tmp;
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return modCount;
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
package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size < container.length) {
            container[size++] = value;
        } else {
            grow(value);
            container[++size] = value;
        }
        modCount++;
    }

    public void grow(T value) {
        if (container.length != 0) {
            container = Arrays.copyOf(container, container.length * 2);
        } else {
            container = Arrays.copyOf(container, 10);
        }
    }

    public T check(int index) {
        Objects.checkIndex(index, size);
        T tmp = container[index];
        return tmp;
    }

    @Override
    public T set(int index, T newValue) {
        T tmp = check(index);
        container[index] = newValue;
        return tmp;
    }

    @Override
    public T remove(int index) {
        T tmp = check(index);
        System.arraycopy(container, index + 1, container, index, modCount - index);
        container[size - 1] = null;
        modCount++;
        size--;
        return tmp;
    }

    @Override
    public T get(int index) {
        return check(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    return index < size;
                }
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return container[index++];
                }
            }
        };
    }
}
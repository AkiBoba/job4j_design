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
        if (modCount < container.length) {
            container[size++] = value;
            modCount++;
        } else {
            grow(value);
        }
    }

    public void grow(T value) {
            container = Arrays.copyOf(container, container.length * 2);
            container[++size] = value;
            modCount++;
    }

    @Override
    public T set(int index, T newValue) {
            T tmp = container[Objects.checkIndex(index, container.length)];
            container[index] = newValue;
        return tmp;
    }

    @Override
    public T remove(int index) {
        T tmp = container[Objects.checkIndex(index, container.length)];
        System.arraycopy(container, index + 1, container, index, modCount - index);
        container[size - 1] = null;
        modCount++;
        size--;
        return tmp;
    }

    @Override
    public T get(int index) {
            return container[Objects.checkIndex(index, container.length)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size && ;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    return container[index++];
                }
            }
        };
    }
}
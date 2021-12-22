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
            container[modCount] = value;
            modCount++;
        } else {
            container = Arrays.copyOf(container, container.length * 2);
            modCount++;
            container[modCount] = value;
        }
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
        modCount--;
        return tmp;
    }

    @Override
    public T get(int index) {
            return container[Objects.checkIndex(index, container.length)];
    }

    @Override
    public int size() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            int len = expectedModCount;
            int index = expectedModCount;

            @Override
            public boolean hasNext() {
                return len > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    return container[index - len--];
                }
            }
        };
    }
}
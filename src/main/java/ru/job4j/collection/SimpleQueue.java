package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T res = in.pop();
        out.pushQ(res);
        return res;
    }

    public void push(T value) {
        in.pushQ(value);
    }
}

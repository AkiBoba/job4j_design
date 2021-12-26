package ru.job4j.collection.list;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Vladimir Likhachev
 */
public class SimpleLinkedList<E> implements List<E> {
    private Nod head;
    private Nod tail;
    private static int size;
    private static int modCount;

    public SimpleLinkedList() {
        this.head = null;
    }

    @Override
    public void add(E value) {
        final Nod<E> l = tail;
        final Nod<E> newNode = new Nod<>(l, value, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Nod tmp = head;
        if (index == 0) {
            return (E) tmp.data;
        } else {
            return (E) tmp.next.data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(index++);
            }
        };
    }
}

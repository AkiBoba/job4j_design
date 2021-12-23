package ru.job4j.collection.list;

import org.w3c.dom.Node;

import java.util.Iterator;

public class SimpleLinkedList<E> implements List<E> {
    private E[] container;
    int modCount;
    int size;

    public SimpleLinkedList() {
        this.container = (E[]) new List[10];
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E element) {
            this.item = element;
            this.next = null;
            this.prev = prev == null ? null : prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> item = new Node<>(value);

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}

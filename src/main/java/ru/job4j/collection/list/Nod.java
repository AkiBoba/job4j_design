package ru.job4j.collection.list;

/**
 * @author Vladimir Likhachev
 */
public class Nod<E> {
    public E data;
    public Nod<E> prev;
    public Nod<E> next;

    public Nod(Nod<E> prev, E data, Nod<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

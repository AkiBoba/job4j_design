package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Vladimir Likhachev
 */
public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean res = !contains(value);
        if (res) {
            set.add(value);
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T val : set) {
            if (Objects.equals(value, val)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

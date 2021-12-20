package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * каркас универсального хранилища.
 * @param <T>
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
       if (!storage.containsKey(model.getId())) {
           storage.put(model.getId(), model);
       }
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        if (storage.containsKey(id)) {
            storage.put(id, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            res = true;
        }
        return res;
    }

    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }
}
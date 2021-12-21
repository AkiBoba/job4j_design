package ru.job4j.generic;

import javax.lang.model.type.NullType;
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
       storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return  storage.put(id, model) == null;
    }

    @Override
    public boolean delete(String id) {
           return storage.remove(id, storage.get(id));
    }

    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }
}
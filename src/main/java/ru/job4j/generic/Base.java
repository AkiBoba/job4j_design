package ru.job4j.generic;

/**
 * Все модели наследуются от базовой модели ru.job4j.generic.Base.
 */
public abstract class Base {

    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

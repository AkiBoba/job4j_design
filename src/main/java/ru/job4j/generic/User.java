package ru.job4j.generic;

/**
 * модель данных User, которая будет наследовать класс Base
 */
public class User extends Base {

    private final String username;

    public User(String id, String name) {
        super(id);
        this.username = name;
    }

    public String getUsername() {
        return username;
    }
}

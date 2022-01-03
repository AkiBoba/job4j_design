package ru.job4j.map;

import java.util.Calendar;
/**
 * @author Vladimir Likhachev
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}

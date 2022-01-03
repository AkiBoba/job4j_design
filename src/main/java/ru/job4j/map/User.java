package ru.job4j.map;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User user1 = new User("Ivan", 1, Calendar.getInstance());
        User user2 = new User("Ivan", 1, Calendar.getInstance());
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (User user : map.keySet()) {
            System.out.println("user = " + user);
        }
    }
}

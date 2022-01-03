package ru.job4j.map;

import java.util.*;

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
        Calendar calendar = new GregorianCalendar(2017, 0, 25, 12, 50, 45);
        User user1 = new User("Ivan", 1, calendar);
        User user2 = new User("Ivan", 1, calendar);
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (User user : map.keySet()) {
            System.out.println("user = " + user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }
}

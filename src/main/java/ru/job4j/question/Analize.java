package ru.job4j.question;

import java.util.*;

/**
 * @author Vladimir Likhachev
 */
public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        Info info = new Info(0, 0, 0);
        Map<Integer, String> mapOfCurrent = setToMap(current);
        for (User user : previous) {
            if (mapOfCurrent.containsKey(user.getId())) {
                if (!mapOfCurrent.remove(user.getId(), user.getName())) {
                    info.setChanged(++changed);
                    mapOfCurrent.remove(user.getId());
                    continue;
                }
                continue;
            }
            info.setDeleted(++deleted);
        }
        if (!mapOfCurrent.isEmpty()) {
            info.setAdded(mapOfCurrent.size());
        }
        return info;
    }

    private static Map<Integer, String> setToMap(Set<User> set) {
        Map<Integer, String> map = new HashMap<>();
        for (User user : set) {
            map.put(user.getId(), user.getName());
        }
        return map;
    }
}

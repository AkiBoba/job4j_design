package ru.job4j.io;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (!validate(args)) {
            throw new IllegalArgumentException();
        }
        List<String> list = List.of(args);
        list.stream()
                .map(s -> s.split("="))
                .forEach(s -> values.put(s[0].substring(1), s[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private boolean validate(String[] args) {
        boolean result = true;
        if (args.length == 0) {
            return false;
        }
        String regex = "^-.*=[^=]{1,}";
        Predicate<String> filter = line -> Pattern.matches(regex, line);
        for (String str : args) {
            if (!filter.test(str)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

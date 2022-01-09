package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Vladimir Likhachev
 */
public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        Predicate<String> filter = line -> line
                .split(" ")[line.split(" ").length - 2]
                .equals("404");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(filter).forEach(list :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out :: println);
    }
}

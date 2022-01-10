package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        Predicate<String> filter = line -> !line.startsWith("#") || !line.startsWith(" ");
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(filter)
                    .forEach(s -> values.put(s.split("=")[0], s.split("=")[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        throw new UnsupportedOperationException("Don't impl this method yet!");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties.txt"));
    }
}


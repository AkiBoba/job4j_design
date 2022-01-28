package ru.job4j.testtask;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import static ru.job4j.io.Search.search;

public class Find {
    public static void validation(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Недостаточно аргументов для корректной работы приложения");
        }
        ArgsName argsName = ArgsName.of(args);
        String dirForFind = argsName.get("d");
        String typeFind = argsName.get("t");
        if (!Paths.get(dirForFind).toFile().exists()
                && !Paths.get(dirForFind).toFile().isDirectory()) {
            throw new IllegalArgumentException("Дирректория поиска не существует или это не папка");
        }
        if (!"mask".equals(typeFind) && !"name".equals(typeFind) && !"regex ".equals(typeFind)) {
            throw new IllegalArgumentException("Тип поиска указан не корректно, должен быть mask, name или regex");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName argsName = ArgsName.of(args);
        String dirForFind = argsName.get("d");
        String name = argsName.get("n");
        String typeFind = argsName.get("t");
        String dirForSave = argsName.get("o");
    }
}

package ru.job4j.testtask;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static ru.job4j.io.Search.search;

public class Find {

    public static List<String> excludeFiles(Path root, String name, String typeFind) throws IOException {
        List<String> list = new ArrayList<>();
        Predicate<Path> filter = str -> name.equals(str.toFile().getName());
        if ("mask".equals(typeFind)) {
            String regex = "^" + name.replace(".", "\\.")
                    .replace("*", ".*")
                    .replace("?", ".");
            filter = line -> Pattern.matches(regex, line.toFile().getName());
        }
        if ("regex".equals(typeFind)) {
            filter = line -> Pattern.matches(name, line.toFile().getName());
        }

        Search.search(root, filter).forEach(p -> list.add(p.toString()));
        return list;
    }

    public static void saveLog(List<String> log, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            log.forEach(pw :: println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> validation(String[] args) {
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
        if (!"mask".equals(typeFind) && !"name".equals(typeFind) && !"regex".equals(typeFind)) {
            throw new IllegalArgumentException("Тип поиска указан не корректно, должен быть mask, name или regex");
        }
        return List.of(argsName.get("d"), argsName.get("n"), argsName.get("t"), argsName.get("o"));
    }

    public static void main(String[] args) throws IOException {
        List<String> inArgs = validation(args);
        String dirForFind = inArgs.get(0);
        String name = inArgs.get(1);
        String typeFind = inArgs.get(2);
        String dirForSave = inArgs.get(3);

        saveLog(excludeFiles(Paths.get(dirForFind), name, typeFind), dirForSave);

    }
}

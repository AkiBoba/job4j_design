package ru.job4j.io;

import ru.job4j.list.List;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;

import static ru.job4j.io.Search.search;

public class Zip {
    public static void packFiles(List<File> sources, File target, Predicate predicate) {
        if (sources.size() > 1) {
            sources.forEach(s ->  packSingleFile(s.getAbsoluteFile(), target, predicate));
        }
        sources.forEach(s -> packSingleFile(s.getAbsoluteFile(), target, predicate));
    }

    public static void packSingleFile(File source, File target, Predicate predicate) {
        if (predicate.test(source.getName())) {
            throw new IllegalArgumentException();
        }
        try (ZipOutputStream zip = new ZipOutputStream(new
                BufferedOutputStream(new FileOutputStream(target)))) {

            zip.putNextEntry(new ZipEntry(source.getPath()));

            try (BufferedInputStream out = new BufferedInputStream(new
                    FileInputStream(source))) {

                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<File> excludeFiles(Path root, String exclude) throws IOException {
        Predicate<Path> condition;
        return List<File> list;
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(new String[] {args[0], args[1], args[2]});
        String arg1 = argsName.get("d");
        String arg2 = argsName.get("o");
        String arg3 = argsName.get("e");
        Predicate<Path> filter = name -> name.toFile().getName().endsWith(arg3);
        packFiles(
                search(Paths.get(arg1), filter),
                new File(arg2),
                filter
        );
    }
}

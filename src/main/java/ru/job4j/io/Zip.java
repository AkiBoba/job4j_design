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
    public static void packFiles(List<Path> sources, File target) {
        if (sources.size() > 1) {
            sources.forEach(s ->  packSingleFile(s.toAbsolutePath(), target));
        }
        sources.forEach(s -> packSingleFile(s.toAbsolutePath(), target));
    }

    public static void packSingleFile(Path source, File target) {

        try (ZipOutputStream zip = new ZipOutputStream(new
                BufferedOutputStream(new FileOutputStream(target)))) {

            zip.putNextEntry(new ZipEntry(source.toString()));

            try (BufferedInputStream out = new BufferedInputStream(new
                    FileInputStream(source.toString()))) {

                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

       static List<Path> excludeFiles(Path root, String exclude) throws IOException {
        Predicate<Path> condition = name -> !name.endsWith(exclude);
        List<Path> list = new ArrayList<>();
            list.addAll(search(root, condition));
        return (List<Path>) list;
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(new String[] {args[0], args[1], args[2]});
        String arg1 = argsName.get("d");
        String arg2 = argsName.get("o");
        String arg3 = argsName.get("e");
        packFiles(
                excludeFiles(Paths.get(arg1), arg3),
                new File(arg2)
        );
    }
}

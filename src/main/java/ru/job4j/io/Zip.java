package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<String> sources, File target) {
//        sources.forEach(s ->  packSingleFile(Paths.get(s).toFile(), target));
        File file;
        for (String str : sources) {
            file = Paths.get(str).toFile();
            packSingleFile(file, target);
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> excludeFiles(Path root, String exclude) throws IOException {
        List<String> list = new ArrayList<>();
        Predicate<Path> condition = name -> !name.toString() .endsWith(exclude);
        Search.search(root, condition).forEach(p -> list.add(p.toString()));
        return list;
    }

        public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(new String[] {args[0], args[1], args[2]});
        String arg1 = argsName.get("d");
        String arg2 = argsName.get("o");
        String arg3 = argsName.get("e");
        packFiles(excludeFiles(Paths.get(arg1), arg3), Paths.get(arg1).toFile());
    }
}
package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<String> sources, File target) {
        File file;
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String str : sources) {
                file = Paths.get(str).toFile();
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        Predicate<Path> condition = name -> !name.toString().endsWith(exclude);
        Search.search(root, condition).forEach(p -> list.add(p.toString()));
        return list;
    }

    public static void validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Недостаточно аргументов для корректной работы приложения");
        }
        ArgsName argsName = ArgsName.of(new String[] {args[0]});
        String dirForZip = argsName.get("d");
        if (!Paths.get(dirForZip).toFile().exists() && !Paths.get(dirForZip).toFile().isDirectory()) {
            throw new IllegalArgumentException("Дирректория для архивирования не существует или это не папка");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName argsName = ArgsName.of(args);
        String dirForZip = argsName.get("d");
        String zipDirOut = argsName.get("o");
        String exclude = argsName.get("e");
        if (exclude.startsWith(".")) {
            exclude = exclude.substring(1);
        }
        packFiles(excludeFiles(Paths.get(dirForZip), exclude), Paths.get(zipDirOut).toFile());
    }
}
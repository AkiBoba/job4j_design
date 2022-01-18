package ru.job4j.io;

import ru.job4j.list.List;

import java.io.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<File> sources, File target) {

    }

    public static void packSingleFile(File source, File target) {
        if (!source.exists()) {

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

    private static boolean validate(String[] args) {
        boolean result = true;
        if (args.length == 0) {
            return false;
        }
        String regex = "^-[^=]+=[^=]+";
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
        if (!validate(args)) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(new String[] {args[0], args[2]});
        String arg1 = argsName.get("d");
        String arg2 = argsName.get("o");
        packSingleFile(
                new File(arg1),
                new File(arg2)
        );
    }
}

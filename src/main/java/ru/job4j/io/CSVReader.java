package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Vladimir Likhachev
 */
public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validation(argsName);
        String delimiter = argsName.get("delimiter");
        File file = new File(argsName.get("path"));
        Scanner scanner = new Scanner(file);
        String string = scanner.nextLine();
        List<String> result = new ArrayList<>();
        List<String> list = Arrays.stream(string.split(delimiter)).toList();
        List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList();
        result.add(newString(list, string, delimiter, filters));
        while (scanner.hasNext()) {
            string = scanner.nextLine();
            result.add(newString(list, string, delimiter, filters));
        }
        outResult(argsName.get("out"), result);
    }

    private static String newString(List<String> list, String string, String delimiter, List<String> filters) {
        String newstring = "";
        for (int index = 0; index < filters.size(); index++) {
            newstring = newstring.concat(string.split(delimiter)[list.indexOf(filters.get(index))] + delimiter);
        }
        return newstring.substring(0, newstring.length() - 1);
    }

    private static void outResult(String outTo, List<String> result) {
        String data = String.join(
                System.lineSeparator(),
                result.stream().toList()
        );

        if ("stdout".equals(outTo)) {
            System.out.println(data);
        }
        try {
            Files.writeString(new File(outTo).toPath(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validation(ArgsName args) {
        String res = args.get("path");
        if (!Paths.get(res).toFile().exists()) {
            throw new IllegalArgumentException("Файл с данными не существует");
        }
        if (!args.get("delimiter").equals(";")) {
            throw new IllegalArgumentException("Ключ delimiter имеет недопустимые значения");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

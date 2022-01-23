package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Vladimir Likhachev
 */
public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        File file = new File(argsName.get("path"));
        Scanner scanner = new Scanner(file);
        String string = scanner.nextLine();
        List<String> result = new ArrayList<>();
        List<String> list = Arrays.stream(string.split(argsName.get("delimiter"))).toList();
        List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList();
        result.add(string.split(argsName.get("delimiter"))[list.indexOf(filters.get(0))]);
        result.add(";");
        result.add(string.split(argsName.get("delimiter"))[list.indexOf(filters.get(1))]);
        result.add(System.lineSeparator());
        while (scanner.hasNext()) {
            string = scanner.nextLine();
            result.add(string.split(argsName.get("delimiter"))[list.indexOf(filters.get(0))]);
            result.add(";");
            result.add(string.split(argsName.get("delimiter"))[list.indexOf(filters.get(1))]);
            result.add(System.lineSeparator());
        }
//        BufferedWriter bw = new BufferedWriter(new FileWriter(argsName.get("out")));
//        for (String str : result) {
//            try {
//                bw.write(str);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(argsName.get("out")));
        for (String str : result) {
            try {
                bs.write(str.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result.forEach(System.out :: print);
    }

}

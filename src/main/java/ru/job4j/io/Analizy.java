package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Analizy {
    public void unavailable(String source, String target) {

        boolean flag = false;
        try (PrintWriter outresults = new PrintWriter(new FileOutputStream(target))) {
            Predicate<String> filter = line -> line.startsWith("400") || line.startsWith("500");
            try (BufferedReader read = new BufferedReader(new FileReader(source))) {
                List<String> list = new ArrayList<>();
                String line = read.readLine();
                while (line != null) {
                    if (filter.test(line)) {
                        list.add(line.split(" ")[1]);
                        flag = true;
                    } else if (flag) {
                        list.add(line.split(" ")[1]);
                        outresults.println(list.get(0) + ";" + list.get(list.size() - 1) + " ");
                        list.clear();
                        flag = false;
                    }
                    line = read.readLine();
                }
            }
            catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

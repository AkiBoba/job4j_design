package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {

        Path path = Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
//        System.out.println(path.getFileName());
    }
}
package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> set = new HashSet<>();
    List<FileProperty> list = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path line = file.toAbsolutePath();
        FileProperty fileProperty = new FileProperty(Files.size(line), String.valueOf(line.getFileName()));

        if (!set.add(fileProperty)) {
            list.add(fileProperty);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<FileProperty> getDuplicates() {
        return list;
    }
}

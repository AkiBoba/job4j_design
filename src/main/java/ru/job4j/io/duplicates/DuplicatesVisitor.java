package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, String> map = new HashMap();
    private final Set<String> list = new HashSet<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path line = file.toAbsolutePath();
        FileProperty fileProperty = new FileProperty(Files.size(line), String.valueOf(line.getFileName()));

        if (map.containsKey(fileProperty)) {
            list.add(map.get(fileProperty));
            list.add(String.valueOf(line));
        }
        map.put(fileProperty, String.valueOf(line.toAbsolutePath()));
        return FileVisitResult.CONTINUE;
    }

    public Set<String> getDuplicates() {
        return list;
    }
}

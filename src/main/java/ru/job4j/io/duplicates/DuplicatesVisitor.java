package ru.job4j.io.duplicates;

import ru.job4j.collection.list.List;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path line = file.toAbsolutePath();
        System.out.println(line.getFileName() + " " + Files.size(line));
        FileProperty fileProperty = new FileProperty(Files.size(line), String.valueOf(line.getFileName()));

        return super.visitFile(file, attrs);
    }
}

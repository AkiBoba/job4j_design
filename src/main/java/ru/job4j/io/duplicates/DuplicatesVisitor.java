package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

//        LinkedList<FileProperty> list = new LinkedList<>();


        Path line = file.toAbsolutePath();
//        System.out.println(line.getFileName() + " " + Files.size(line));
        FileProperty fileProperty = new FileProperty(Files.size(line), String.valueOf(line.getFileName()));

        Set<FileProperty> set = new HashSet<>();
        List<FileProperty> list = new ArrayList<>();

        if (!set.add(fileProperty)) {
            list.add(fileProperty);
            System.out.println(fileProperty.getName());
        }
//

        return super.visitFile(file, attrs);
    }
}

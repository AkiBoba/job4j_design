package ru.job4j.io;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Test
    public void whenPrint() {
        String path = "test.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(path, "testresults.csv");
    }
}
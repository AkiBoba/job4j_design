package ru.job4j.io;

import java.util.List;
import java.util.Scanner;

/**
 * @author Vladimir Likhachev
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    Scanner in = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.print("Напишите что нибудь: ");
        String string = in.nextLine();
        switch (string) {

            default -> run();
        }
    }

    private List<String> readPhrases() {
        return null;
    }

    private void saveLog(List<String> log) {

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}

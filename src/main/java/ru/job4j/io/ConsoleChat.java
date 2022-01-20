package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
    List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run(String ask, String status) {
        String nextbotAnswers;
        String next = "Напишите что нибудь еще: ";
        List<String> list = readPhrases();
        System.out.println(ask);
        String string = in.nextLine();
        log.add(string);
        switch (string) {
            case OUT -> {
                log.forEach(System.out::println);
                saveLog(log);
                System.exit(0);
            }
            case STOP -> run(next, STOP);
            case CONTINUE -> {
                nextbotAnswers = list.get((int) (Math.random() * list.size()));
                log.add(nextbotAnswers);
                System.out.println(nextbotAnswers);
                run(next, CONTINUE);
                    }
            default -> {
                if (status.equals(STOP)) {
                    run(next, STOP);
                    }
                nextbotAnswers = list.get((int) (Math.random() * list.size()));
                log.add(nextbotAnswers);
                System.out.println(nextbotAnswers);
                run(next, status);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().forEach(phrases::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("chat.txt", Charset.forName("WINDOWS-1251")))) {
            log.forEach(pw :: println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("bot.txt", "Напишите что нибудь: ");
        cc.run(cc.botAnswers, CONTINUE);
    }
}

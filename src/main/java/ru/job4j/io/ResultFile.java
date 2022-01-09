package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * @author Vladimir Likhachev
 */
public class ResultFile {
    public void multiple(int size) {
        int[][] result = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    result[row][col] = (row + 1) * (col + 1);
                    out.write(String.valueOf(result[row][col]).getBytes());
                    out.write((" ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResultFile resultFile = new ResultFile();
        resultFile.multiple(3);
    }
}


package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * @author Vladimir Likhachev
 */
public class ResultFile {
    public static void multiple(int[][] matrix) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] ints : matrix) {
                for (int col = 0; col < matrix.length; col++) {
                    out.write(String.valueOf(ints[col]).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2},
                {2, 4}
        };
        ResultFile.multiple(matrix);
    }
}


package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int sum = 0;
        for (int[] datum : data) {
            sum += datum.length;
        }
        if (sum == 0) {
            return false;
        }
        boolean res = false;
        if (row == data.length) {
            row = 0;
            column++;
        }
        while (row != data.length) {
            if (data[row].length == 0) {
                while (data[row].length == 0) {
                    row++;
                }
                res = true;
                break;
            } else if (data[row].length - 1 >= column) {
                res =true;
                break;
            } else {
                row++;
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row++][column];
    }
}
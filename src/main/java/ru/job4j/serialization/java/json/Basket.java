package ru.job4j.serialization.java.json;

import java.util.Arrays;

/**
 * @author Vladimir Likhachev
 */
public class Basket {
    String[] products;
    boolean paid;

    public Basket(String[] products, boolean paid) {
        this.products = products;
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Basket{"
                + "products=" + Arrays.toString(products)
                + ", paid="
                + paid
                + '}';
    }
}

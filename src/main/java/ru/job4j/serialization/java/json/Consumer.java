package ru.job4j.serialization.java.json;

import java.util.Arrays;

/**
 * @author Vladimir Likhachev
 */
public class Consumer {
    private boolean company;
    private int discount;
    private String name;
    private Address adress;
    private String[] conditions;
    private Basket basket;

    public Consumer(boolean company, int discount, String name, Address adress, String[] conditions, Basket products) {
        this.company = company;
        this.discount = discount;
        this.name = name;
        this.adress = adress;
        this.conditions = conditions;
        this.basket = products;
    }

    @Override
    public String toString() {
        return "Consumer{"
                + "company="
                + company + ", discount="
                + discount + ", name='" + name + '\''
                + ", adress=" + adress
                + ", conditions=" + Arrays.toString(conditions)
                + ", basket=" + basket
                + '}';
    }
}

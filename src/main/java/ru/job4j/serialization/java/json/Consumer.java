package ru.job4j.serialization.java.json;

import java.util.Arrays;

/**
 * @author Vladimir Likhachev
 */
public class Consumer {
    private boolean company;
    private int discount;
    private String name;
    private Address address;
    private String[] conditions;

    public Consumer(boolean company, int discount, String name, Address address, String[] conditions) {
        this.company = company;
        this.discount = discount;
        this.name = name;
        this.address = address;
        this.conditions = conditions;
    }


    @Override
    public String toString() {
        return "Consumer{"
                +
                "company=" + company
                +
                ", discount=" + discount
                +
                ", name='" + name + '\''
                +
                ", address=" + address
                +
                ", conditions=" + Arrays.toString(conditions)
                +
                '}';
    }
}

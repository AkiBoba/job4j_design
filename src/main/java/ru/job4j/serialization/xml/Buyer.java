package ru.job4j.serialization.xml;

import java.util.Arrays;

/**
 * @author Vladimir Likhachev
 */
public class Buyer {
    private boolean company;
    private int discount;
    private String name;
    private Contact contact;
    private String[] conditions;

    public Buyer(boolean company, int discount, String name, Contact contact, String[] conditions) {
        this.company = company;
        this.discount = discount;
        this.name = name;
        this.contact = contact;
        this.conditions = conditions;
    }


    @Override
    public String toString() {
        return "Buyer{"
                +
                "company=" + company
                +
                ", discount=" + discount
                +
                ", name='" + name + '\''
                +
                ", contact=" + contact
                +
                ", conditions=" + Arrays.toString(conditions)
                +
                '}';
    }
}

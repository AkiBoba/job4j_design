package ru.job4j.serialization.java.json;

import java.io.Serializable;

/**
 * @author Vladimir Likhachev
 */
public class Address {
    private final String street;
    private final int house;

    public Address(int house, String street) {
        this.house = house;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='"
                + street + '\''
                + ", house=" + house
                + '}';
    }
}

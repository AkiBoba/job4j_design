package ru.job4j.serialization.java.json;

import java.io.Serializable;

/**
 * @author Vladimir Likhachev
 */
public class Address implements Serializable {
    private final String street;
    private final int house;

    public Address(int house, String street) {
        this.street = street;
        this.house = house;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='"
                + street + '\''
                + ", house=" + house
                + '}';
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }
}

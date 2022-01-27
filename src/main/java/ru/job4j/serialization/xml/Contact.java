package ru.job4j.serialization.xml;

import java.io.Serializable;

/**
 * @author Vladimir Likhachev
 */
public class Contact {
    private final String street;
    private final int house;

    public Contact(int house, String street) {
        this.house = house;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "street='"
                + street + '\''
                + ", house=" + house
                + '}';
    }
}

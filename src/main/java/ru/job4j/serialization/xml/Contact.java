package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author Vladimir Likhachev
 */
@XmlRootElement(name = "contact")
public class Contact {
    @XmlAttribute
    private String street;
    @XmlAttribute
    private int house;

    public Contact() {
    }

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

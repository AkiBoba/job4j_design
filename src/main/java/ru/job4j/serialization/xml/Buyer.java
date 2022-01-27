package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * @author Vladimir Likhachev
 */
@XmlRootElement(name = "buyer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Buyer {
    @XmlAttribute
    private boolean company;
    @XmlAttribute
    private int discount;
    @XmlAttribute
    private String name;
    private Contact contact;
    @XmlElementWrapper(name = "conditions")
    @XmlElement(name = "condition")
    private String[] conditions;

    public Buyer() {
    }

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

    public static void main(String[] args) throws JAXBException {

        final Buyer person = new Buyer(true, 5, "MMM",
                new Contact(3, "Wall Street"),
                new String[] {"Отсрочка", "Доставка"}
        );

        JAXBContext context = JAXBContext.newInstance(Buyer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}

package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
/**
 * @author Vladimir Likhachev
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Buyer person = new Buyer(true, 5, "MMM",
                new Contact(3, "Wall Street"),
                new String[] {"Отсрочка", "Доставка"}
        );
        JAXBContext context = JAXBContext.newInstance(Buyer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Buyer result = (Buyer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}

package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vladimir Likhachev
 */
public class Info {
    public static void main(String[] args) {
        final Buyer buyer = new Buyer(true, 5, "MMM",
                new Contact(3, "Wall Street"),
                new String[] {"Отсрочка", "Доставка"}
        );

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(buyer));

        final String consumerJson =
                "{"
                        + "\"company\":true,"
                        + "\"discount\":" + 5
                        + ", \"name\":\"MMM\","
                        + "\"contact\":"
                        + "{"
                        + "\"street\":"
                        + "\"Wall Street\","
                        + "\"house\":" + 3
                        + "},"
                        + "\"conditions\":"
                        + "[\"Отсрочка\",\"Доставка\"]"
                        + "}";
        final Buyer personMod = gson.fromJson(consumerJson, Buyer.class);
        System.out.println(personMod);
    }
}

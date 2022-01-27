package ru.job4j.serialization.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vladimir Likhachev
 */
public class Shop {
    public static void main(String[] args) {
        final Consumer consumer = new Consumer(true, 5, "MMM",
                new Address(3, "Wall Street"),
                new String[]{"Отсрочка", "Доставка"},
                new Basket(new String[] {"ГБЦ", "МКПП"}, true));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(consumer));

        final String consumerJson =
                "{"
                        + "\"company\":true,"
                        + "\"discunt\":" + 5
                        + "\"name\":\"MMM\","
                        + "\"address\":"
                        + "{"
                        + "\"street\":"
                        + "\"Wall Street\","
                        + "\"house\":" + 3
                        + "},"
                        + "\"condition\":"
                        + "[\"Отсрочка\",\"Доставка\"],"
                        + "\"basket\":"
                        + "{"
                        + "\"products\":"
                        + "[\"ГБЦ\",\"МКПП\"],"
                        + "\"paid\":"
                        + "\"true\""
                        + "}"
                        + "}";
        final Consumer personMod = gson.fromJson(consumerJson, Consumer.class);
        System.out.println(personMod);
    }
}
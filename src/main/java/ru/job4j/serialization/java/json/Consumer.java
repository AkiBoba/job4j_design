package ru.job4j.serialization.java.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.json.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isCompany() {
        return company;
    }

    public int getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
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

    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonAddress = new JSONObject("{"
                + "\"street\":"
                + "\"Wall Street\","
                + "\"house\":" + 3
                + "}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Отсрочка");
        list.add("Доставка");
        JSONArray jsonConditions = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Consumer person = new Consumer(false, 30, "mmm",
                new Address(13, "5 St"),
                new String[]{"Предоплата", "Самовывоз"}
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("company", person.isCompany());
        jsonObject.put("discount", person.getDiscount());
        jsonObject.put("name", person.getName());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("statuses", jsonConditions);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person));
    }

}

package ru.job4j.jdbc;

import ru.job4j.io.Config;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vladimir Likhachev
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("app.properties");
        config.load();
        String url = "hibernate.connection.url";
        String login = "hibernate.connection.username";
        String password = "hibernate.connection.password";
        try (Connection connection = DriverManager.getConnection(config.value(url),
                config.value(login), config.value(password))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

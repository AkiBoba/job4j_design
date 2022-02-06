package ru.job4j.jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * @author Vladimir Likhachev
 */

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException {
        String driver = "hibernate.connection.driver_class";
        String url = "hibernate.connection.url";
        String login = "hibernate.connection.username";
        String password = "hibernate.connection.password";
        Class.forName(properties.get(driver).toString());
        try (Connection connection = DriverManager.getConnection(properties.get(url).toString(),
                properties.get(login).toString(), properties.get(password).toString())) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable(String tableName) {

    }

    public void dropTable(String tableName) {
    }

    public void addColumn(String tableName, String columnName, String type) {
    }

    public void dropColumn(String tableName, String columnName) {
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("app.properties"));
        TableEditor tableEditor = new  TableEditor(properties);
        tableEditor.createTable("demo_stat");

    }
}

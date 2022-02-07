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

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        String driver = "hibernate.connection.driver_class";
        String url = "hibernate.connection.url";
        String login = "hibernate.connection.username";
        String password = "hibernate.connection.password";
        Class.forName(properties.get(driver).toString());
        return DriverManager.getConnection(properties.get(url).toString(),
                properties.get(login).toString(), properties.get(password).toString());
    }

    private void initConnection() throws ClassNotFoundException {
        try (Connection connection = getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.printf("Успешное соединение с БД %s!%n", metaData.getURL());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void testExecute(String tableName, String sql) {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                System.out.println(getTableScheme(connection, tableName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s();", tableName
        );
        testExecute(tableName, sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table if exists %s;", tableName
        );
        testExecute(tableName, sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD %s %s", tableName, columnName, type
        );
        testExecute(tableName, sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s drop %s", tableName, columnName
        );
        testExecute(tableName, sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s rename %s to %s", tableName, columnName, newColumnName
        );
        testExecute(tableName, sql);
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
        tableEditor.createTable("demo_table");
        tableEditor.addColumn("demo_table", "name", "text");
        tableEditor.renameColumn("demo_table", "name", "rename");
        tableEditor.dropColumn("demo_table", "rename");
        tableEditor.dropTable("demo_table");
    }
}

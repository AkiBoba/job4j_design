package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "app_conf.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("surname"), isEmptyOrNullString());
    }

    @Test
    public void whenPairWithoutSpace() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("surname"), isEmptyOrNullString());
    }
}
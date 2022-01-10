package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "app.properties.txt";
        Config config = new Config(path);
        config.load();
//        assertThat(config.value("name"), is("Petr Arsentev"));
    }
}
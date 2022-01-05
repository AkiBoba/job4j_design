package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vladimir Likhachev
 */
public class SimpleMapTest {

    @Test
    public void ifPut() {
        SimpleMap simpleMap = new SimpleMap();
        assertThat(simpleMap.put(123, "123"), is(true));
        assertThat(simpleMap.put(129, "12"), is(true));

    }

    @Test
    public void whenNext() {
        SimpleMap simpleMap = new SimpleMap();
        simpleMap.put(123, "123");
        simpleMap.put(129, "12");
        Iterator iterator = simpleMap.iterator();
        assertThat(iterator.next(), is(129));
        assertThat(iterator.next(), is(123));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void findValue() {
        SimpleMap simpleMap = new SimpleMap();
        simpleMap.put(123, "123");
        simpleMap.put(129, "12");
        assertThat(simpleMap.get(123), is("123"));
    }

    @Test
    public void whenRemove() {
        SimpleMap simpleMap = new SimpleMap();
        simpleMap.put(123, "123");
        simpleMap.put(129, "12");
        assertThat(simpleMap.remove(123), is(true));
        assertEquals(simpleMap.get(123), null);
    }

    @Test
    public void whenExpand() {
        SimpleMap simpleMap = new SimpleMap();
        simpleMap.put(123, "123");
        simpleMap.put(129, "12");
        simpleMap.put(300, "1300");
        simpleMap.put(19, "190");
        simpleMap.put(400, "3");
        simpleMap.put(500, "002");
        simpleMap.put(600, "1600");
        simpleMap.put(1900, "1900");
        simpleMap.put(10, "1001");
        simpleMap.put(40550, "3555");
        simpleMap.put(550, "102");
        simpleMap.put(660, "66");
        simpleMap.put(19120, "192300");
        simpleMap.put(1012, "100123");
    }

}
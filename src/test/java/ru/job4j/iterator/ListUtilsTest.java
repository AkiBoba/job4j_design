package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input.get(1), is(2));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input.get(1), is(2));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> filter = x -> x == 2 || x == 3;
        ListUtils.removeIf(input, filter);
        assertThat(input.get(1), is(4));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> filter = x -> x == 2 || x == 3;
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input.get(1), is(0));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> remove = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, remove);
        assertThat(input.get(1), is(4));
    }
}
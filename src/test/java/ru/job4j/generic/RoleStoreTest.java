package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

        @Test
        public void whenAddAndFindThenRolenameIsPetr() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            Role result = store.findById("1");
            assertThat(result.getRolename(), is("June"));
        }

        @Test
        public void whenAddAndFindThenRoleIsNull() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            Role result = store.findById("10");
            assertNull(result);
        }

        @Test
        public void whenAddDuplicateAndFindRolenameIsPetr() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            store.add(new Role("1", "Prof"));
            Role result = store.findById("1");
            assertThat(result.getRolename(), is("June"));
        }

        @Test
        public void whenReplaceThenRolenameIsMaxim() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            store.replace("1", new Role("1", "Prof"));
            Role result = store.findById("1");
            assertThat(result.getRolename(), is("Prof"));
        }

        @Test
        public void whenNoReplaceUserThenNoChangeRolename() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            store.replace("10", new Role("10", "Prof"));
            Role result = store.findById("1");
            assertThat(result.getRolename(), is("June"));
        }

        @Test
        public void whenDeleteUserThenRoleIsNull() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            store.delete("1");
            Role result = store.findById("1");
            assertNull(result);
        }

        @Test
        public void whenNoDeleteUserThenRolenameIsPetr() {
            RoleStore store = new RoleStore();
            store.add(new Role("1", "June"));
            store.delete("10");
            Role result = store.findById("1");
            assertThat(result.getRolename(), is("June"));
        }
    }
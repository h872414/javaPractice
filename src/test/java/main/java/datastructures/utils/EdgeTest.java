package main.java.datastructures.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EdgeTest {
    Edge edge = new Edge();

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> new Edge(null));
    }

    @Test
    void simpleTest() {
        edge.add(2);
        assertThat(2, is(edge.getNext()));
        assertThrows(NullPointerException.class, () -> edge.getNext());
        edge.add(1);
        edge.add(2);
        assertThat(1, is(edge.getNext()));
        assertThat(2, is(edge.getNext()));
        assertThrows(NullPointerException.class, () -> edge.getNext());
    }

    @Test
    void addNullTest() {
        assertThrows(NullPointerException.class, () -> edge.add(null));
    }
}

package main.java.datastructures.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EdgeTest {
    Edge<Integer> edge = new Edge<>();

    @Test
    void constructor_Test() {
        assertThrows(NullPointerException.class, () -> new Edge<Integer>(null));
    }

    @Test
    void simple_Test() {
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
    void add_Null_Test() {
        assertThrows(NullPointerException.class, () -> edge.add(null));
    }

    @Test
    void isEmpty_EmptyEdge_Test() {
        assertThat(edge.isEmpty(), is(true));
    }

    @Test
    void isEmpty_NotEmptyEdge_Test() {
        edge.add(1);
        assertThat(edge.isEmpty(), is(false));
    }
}

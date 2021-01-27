package main.java.datastructures.heap;

import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeapTest {
    private Heap heap = new Heap();

    @Test
    void simpleTest() {
        heap.insert(35);
        assertThat(heap.max().getKey(), is(35));
        heap.insert(33);
        assertThat(heap.max().getKey(), is(35));
        assertThat(heap.max().getLeftChild().getKey(), is(33));
        heap.insert(42);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        heap.insert(10);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        heap.insert(14);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getLeftChild().getRightChild().getKey(), is(14));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        heap.insert(19);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getLeftChild().getRightChild().getKey(), is(14));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        assertThat(heap.max().getRightChild().getLeftChild().getKey(), is(19));
        heap.insert(27);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getLeftChild().getRightChild().getKey(), is(14));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        assertThat(heap.max().getRightChild().getLeftChild().getKey(), is(19));
        assertThat(heap.max().getRightChild().getRightChild().getKey(), is(27));
        heap.insert(44);
        assertThat(heap.max().getKey(), is(44));
        assertThat(heap.max().getLeftChild().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getRightChild().getKey(), is(14));
        assertThat(heap.max().getLeftChild().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        assertThat(heap.max().getRightChild().getLeftChild().getKey(), is(19));
        assertThat(heap.max().getRightChild().getRightChild().getKey(), is(27));
        heap.insert(26);
        assertThat(heap.max().getKey(), is(44));
        assertThat(heap.max().getLeftChild().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getRightChild().getKey(), is(14));
        assertThat(heap.max().getLeftChild().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getLeftChild().getLeftChild().getRightChild().getKey(), is(26));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        assertThat(heap.max().getRightChild().getLeftChild().getKey(), is(19));
        assertThat(heap.max().getRightChild().getRightChild().getKey(), is(27));
        heap.insert(31);
        assertThat(heap.max().getKey(), is(44));
        assertThat(heap.max().getLeftChild().getKey(), is(42));
        assertThat(heap.max().getLeftChild().getLeftChild().getKey(), is(33));
        assertThat(heap.max().getLeftChild().getRightChild().getKey(), is(31));
        assertThat(heap.max().getLeftChild().getRightChild().getLeftChild().getKey(), is(14));
        assertThat(heap.max().getLeftChild().getLeftChild().getLeftChild().getKey(), is(10));
        assertThat(heap.max().getLeftChild().getLeftChild().getRightChild().getKey(), is(26));
        assertThat(heap.max().getRightChild().getKey(), is(35));
        assertThat(heap.max().getRightChild().getLeftChild().getKey(), is(19));
        assertThat(heap.max().getRightChild().getRightChild().getKey(), is(27));
        assertThrows(NullPointerException.class, () -> heap.insert(null));
    }

    @Test
    void emptyHeap_WillThrowNullPointerException_Test() {
        assertThrows(NullPointerException.class, () -> heap.max());
    }

    @ParameterizedTest
    @NullSource
    void insertNull_WillThrowNullPointerException_Test(Integer value) {
        assertThrows(NullPointerException.class, () -> heap.insert(value));
    }

    @Test
    void popMaxInEmptyTree_WillThrowNullPointerException_Test() {
        assertThrows(NullPointerException.class, () -> heap.popMax());
    }

    @Test
    void popMaxWithOneElementTest() {
        heap.insert(35);
        assertThat(heap.popMax().getKey(), is(35));
        assertThrows(NullPointerException.class, () -> heap.popMax());
    }

    @Test
    void popMaxTest() {
        heap = buildHeap();
        assertThat(heap.popMax().getKey(), is(44));
        assertThat(heap.popMax().getKey(), is(14));
        assertThat(heap.popMax().getKey(), is(26));
        assertThat(heap.popMax().getKey(), is(10));
        assertThat(heap.popMax().getKey(), is(27));
        assertThat(heap.popMax().getKey(), is(19));
    }

    @Test
    void popLastElementPopAgain_WillThrowNullPointerException() {
        heap = new Heap();
        heap.insert(44);
        assertThat(heap.popMax().getKey(), is(44));
        assertThrows(NullPointerException.class, () -> heap.popMax());
        assertThrows(NullPointerException.class, () -> heap.max());
        assertThat(heap.getLastElement(), nullValue());
    }

    private @NonNull Heap buildHeap() {
        final @NonNull Heap heap = new Heap();
        heap.insert(35);
        heap.insert(33);
        heap.insert(42);
        heap.insert(10);
        heap.insert(14);
        heap.insert(19);
        heap.insert(27);
        heap.insert(44);
        heap.insert(26);
        heap.insert(31);
        return heap;
    }
}

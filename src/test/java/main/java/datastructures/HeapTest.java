package main.java.datastructures;

import lombok.NonNull;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeapTest {
    private Heap heap = new Heap();

    @Test
    void simpleTest() {
        heap.insert(35);
        assertThat(heap.max().getKey(), is(35));
        heap.insert(33);
        assertThat(heap.max().getKey(), is(35));
        assertThat(heap.max().getLeft().getKey(), is(33));
        heap.insert(42);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeft().getKey(), is(33));
        assertThat(heap.max().getRight().getKey(), is(35));
        heap.insert(10);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getRight().getKey(), is(35));
        heap.insert(14);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getLeft().getRight().getKey(), is(14));
        assertThat(heap.max().getRight().getKey(), is(35));
        heap.insert(19);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getLeft().getRight().getKey(), is(14));
        assertThat(heap.max().getRight().getKey(), is(35));
        assertThat(heap.max().getRight().getLeft().getKey(), is(19));
        heap.insert(27);
        assertThat(heap.max().getKey(), is(42));
        assertThat(heap.max().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getLeft().getRight().getKey(), is(14));
        assertThat(heap.max().getRight().getKey(), is(35));
        assertThat(heap.max().getRight().getLeft().getKey(), is(19));
        assertThat(heap.max().getRight().getRight().getKey(), is(27));
        heap.insert(44);
        assertThat(heap.max().getKey(), is(44));
        assertThat(heap.max().getLeft().getKey(), is(42));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getRight().getKey(), is(14));
        assertThat(heap.max().getLeft().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getRight().getKey(), is(35));
        assertThat(heap.max().getRight().getLeft().getKey(), is(19));
        assertThat(heap.max().getRight().getRight().getKey(), is(27));
        heap.insert(26);
        assertThat(heap.max().getKey(), is(44));
        assertThat(heap.max().getLeft().getKey(), is(42));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getRight().getKey(), is(14));
        assertThat(heap.max().getLeft().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getLeft().getLeft().getRight().getKey(), is(26));
        assertThat(heap.max().getRight().getKey(), is(35));
        assertThat(heap.max().getRight().getLeft().getKey(), is(19));
        assertThat(heap.max().getRight().getRight().getKey(), is(27));
        heap.insert(31);
        assertThat(heap.max().getKey(), is(44));
        assertThat(heap.max().getLeft().getKey(), is(42));
        assertThat(heap.max().getLeft().getLeft().getKey(), is(33));
        assertThat(heap.max().getLeft().getRight().getKey(), is(31));
        assertThat(heap.max().getLeft().getRight().getLeft().getKey(), is(14));
        assertThat(heap.max().getLeft().getLeft().getLeft().getKey(), is(10));
        assertThat(heap.max().getLeft().getLeft().getRight().getKey(), is(26));
        assertThat(heap.max().getRight().getKey(), is(35));
        assertThat(heap.max().getRight().getLeft().getKey(), is(19));
        assertThat(heap.max().getRight().getRight().getKey(), is(27));
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
        heap = buildHeap(heap);
        assertThat(heap.popMax().getKey(), is(44));
//        assertThat(heap.popMax().getKey(), is(42));
    }

    @Ignore
    @Test
    void moveLastElementToRootTest() {
        heap = buildHeap(heap);
        assertThat(heap.popMax().getKey(), is(44));
        assertThrows(NullPointerException.class, () -> heap.popMax());
        heap.moveLastElementToRoot();
//        assertThat(heap.getLastElement().getKey(), is(14));
    }

    @Test
    void lastElementTest() {
        heap = buildHeap(heap);
        assertThat(heap.getLastElement(heap.max()).getKey(), is(14));
    }

    private @NonNull Heap buildHeap(@NonNull Heap heap) {
        heap = new Heap();
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

package main.java.datastructures;

import lombok.Value;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinarySearchTreeTest {
    private BinarySearchTree binarySearchTree;

    @Value
    private static class TestCaseForBST {
        int[] nodeValues;
        int[] nodeInOrder;
    }

    @Value
    private static class TestCaseForNexAndPrevioustBST {
        int value;
        int expected;
    }


    private static Collection<TestCaseForBST> dataForSimpleTest() {
        return Arrays.asList(
            new TestCaseForBST(new int[]{2, 1, 3}, new int[]{1, 2, 3}),
            new TestCaseForBST(new int[]{5, 4, 6, 3, 7, 2, 8, 1, 9}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
            new TestCaseForBST(new int[]{12, 10, 9, 2, 1, 55}, new int[]{1, 2, 9, 10, 12, 55}),
            new TestCaseForBST(new int[]{500, 200, 300, 1}, new int[]{1, 200, 300, 500}),
            new TestCaseForBST(
                new int[]{15, 6, 7, 3, 2, 4, 13, 9, 18, 17, 20},
                new int[]{2, 3, 4, 6, 7, 9, 13, 15, 17, 18, 20}
            )
        );
    }

    private static Collection<TestCaseForNexAndPrevioustBST> dataForNextTest() {
        return Arrays.asList(
            new TestCaseForNexAndPrevioustBST(2, 3),
            new TestCaseForNexAndPrevioustBST(3, 4),
            new TestCaseForNexAndPrevioustBST(4, 6),
            new TestCaseForNexAndPrevioustBST(6, 7),
            new TestCaseForNexAndPrevioustBST(7, 9),
            new TestCaseForNexAndPrevioustBST(9, 13),
            new TestCaseForNexAndPrevioustBST(13, 14),
            new TestCaseForNexAndPrevioustBST(15, 17),
            new TestCaseForNexAndPrevioustBST(17, 18),
            new TestCaseForNexAndPrevioustBST(18, 20)
        );
    }

    private static Collection<TestCaseForNexAndPrevioustBST> dataForPreviousTest() {
        return Arrays.asList(
            new TestCaseForNexAndPrevioustBST(3, 2),
            new TestCaseForNexAndPrevioustBST(4, 3),
            new TestCaseForNexAndPrevioustBST(6, 4),
            new TestCaseForNexAndPrevioustBST(7, 6),
            new TestCaseForNexAndPrevioustBST(9, 7),
            new TestCaseForNexAndPrevioustBST(13, 9),
            new TestCaseForNexAndPrevioustBST(14, 13),
            new TestCaseForNexAndPrevioustBST(15, 14),
            new TestCaseForNexAndPrevioustBST(17, 15),
            new TestCaseForNexAndPrevioustBST(18, 17),
            new TestCaseForNexAndPrevioustBST(20, 18)
        );
    }

    @Ignore
    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForSimpleTest")
    void simpleTest(TestCaseForBST testCase) {
    }

    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForSimpleTest")
    void inOrderTest(TestCaseForBST testCase) {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        fillUpTree(testCase.nodeValues, binarySearchTree);
        final ArrayList<Node> expectedNodes = convertIntArrayToArrayList(testCase.nodeInOrder);
        assertThat(expectedNodes, is(binarySearchTree.inOrder()));
    }

    @ParameterizedTest(name = "{index} test case")
    @ValueSource(ints = {1, 2})
    void inOrderRepeatedValueTest(int value) {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(value);
        assertThrows(IllegalArgumentException.class, () -> binarySearchTree.insert(value));
    }

    @ParameterizedTest(name = "{index} test case")
    @NullSource
    void inOrderNullTest(Integer value) {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        assertThrows(NullPointerException.class, () -> binarySearchTree.insert(value));
    }

    @ParameterizedTest(name = "{index}")
    @MethodSource("dataForPreviousTest")
    void previousNodeTest(TestCaseForNexAndPrevioustBST testCase) {
        binarySearchTree = buildTree();
        final Node node = binarySearchTree.search(testCase.value);
        assertThat(testCase.expected, is(binarySearchTree.previousNode(node).getValue()));
    }

    @ParameterizedTest
    @NullSource
    void previousNodeNullTest(final Node node) {
        binarySearchTree = buildTree();
        assertThrows(NullPointerException.class, () -> binarySearchTree.previousNode(node));
    }

    @Test
    void previousNodeFirstElementTest() {
        binarySearchTree = buildTree();
        Node node = buildTree().search(2);
        assertThrows(IllegalArgumentException.class, () -> binarySearchTree.previousNode(node));
    }

    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForNextTest")
    void nextNodeTest(TestCaseForNexAndPrevioustBST testCase) {
        binarySearchTree = buildTree();
        final Node node = binarySearchTree.search(testCase.value);
        assertThat(testCase.expected, is(binarySearchTree.nextNode(node).getValue()));
    }

    @Test
    void nextNodeLastElementTest() {
        binarySearchTree = buildTree();
        Node node = binarySearchTree.search(20);
        assertThrows(IllegalArgumentException.class, () -> binarySearchTree.nextNode(node));
    }

    @ParameterizedTest(name = "{index} test case")
    @NullSource
    @DisplayName("nextNode null test")
    void nextNodeNullTest(Node node) {
        binarySearchTree = buildTree();
        assertThrows(NullPointerException.class, () -> binarySearchTree.nextNode(node));
    }

    @ParameterizedTest(name = "{index} test case")
    @ValueSource(ints = {15, 6, 7, 3, 2, 4, 13, 9, 18, 17, 20})
    void searchTest(int value) {
        binarySearchTree = buildTree();
        assertThat(value, is(binarySearchTree.search(value).getValue()));
    }

    @Test
    void searchInEmptyTreeTest() {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        assertThrows(NullPointerException.class, () -> binarySearchTree.search(1));
    }

    @Test
    void searchNullTest() {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        assertThrows(NullPointerException.class, () -> binarySearchTree.insert(null));
    }

    @Test
    void minTest() {
        binarySearchTree = buildTree();
        assertThat(2, is(binarySearchTree.min().getValue()));
    }

    @Test
    void minInEmptyTreeTest() {
        binarySearchTree = new BinarySearchTree();
        assertThrows(IllegalStateException.class, () -> binarySearchTree.min());
    }

    @Test
    void maxTest() {
        binarySearchTree = buildTree();
        assertThat(20, is(binarySearchTree.max().getValue()));
    }

    @Test
    void maxInEmptyTreeTest() {
        binarySearchTree = new BinarySearchTree();
        assertThrows(IllegalStateException.class, () -> binarySearchTree.max());
    }

    private ArrayList<Node> convertIntArrayToArrayList(int[] array) {
        return (ArrayList<Node>) Arrays.stream(array).mapToObj(Node::new).collect(Collectors.toList());
    }

    private void fillUpTree(int[] array, BinarySearchTree binarySearchTree) {
        for (int nodeValue : array) {
            binarySearchTree.insert(nodeValue);
        }
    }

    private BinarySearchTree buildTree() {
        int[] nodeValues = new int[]{15, 6, 7, 3, 2, 4, 13, 9, 18, 17, 20, 14};
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        fillUpTree(nodeValues, binarySearchTree);
        return binarySearchTree;
    }
}

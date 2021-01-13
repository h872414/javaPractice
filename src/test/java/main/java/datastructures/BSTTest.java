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

class BSTTest {
    private BST bst;

    @Value
    private static class TestCaseForBST {
        int[] nodeValues;
        int[] nodeInOrder;
    }

    @Value
    private static class TestCaseForSearchBST {
        int value;
        int nextValue;
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

    private static Collection<TestCaseForSearchBST> dataForNextTest() {
        return Arrays.asList(
            new TestCaseForSearchBST(2, 3),
            new TestCaseForSearchBST(3, 4),
            new TestCaseForSearchBST(4, 6),
            new TestCaseForSearchBST(6, 7),
            new TestCaseForSearchBST(7, 9),
            new TestCaseForSearchBST(9, 13),
            new TestCaseForSearchBST(13, 14),
            new TestCaseForSearchBST(15, 17),
            new TestCaseForSearchBST(17, 18),
            new TestCaseForSearchBST(18, 20)
        );
    }

    @Ignore
    @DisplayName("BST simpleTest")
    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForSimpleTest")
    void simpleTest(TestCaseForBST testCase) {
    }

    @DisplayName("Inorder test")
    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForSimpleTest")
    void inOrderTest(TestCaseForBST testCase) {
        final BST bst = new BST();
        fillUpTree(testCase.nodeValues, bst);
        final ArrayList<Node> expectedNodes = convertIntArrayToArrayList(testCase.nodeInOrder);
        assertThat(expectedNodes, is(bst.inOrder()));
    }

    @DisplayName("Inorder test with repeated value")
    @ParameterizedTest(name = "{index} test case")
    @ValueSource(ints = {1, 2})
    void inOrderRepeatedValueTest(int value) {
        final BST bst = new BST();
        bst.insert(value);
        assertThrows(IllegalArgumentException.class, () -> bst.insert(value));
    }

    @DisplayName("Inorder test with null")
    @ParameterizedTest(name = "{index} test case")
    @NullSource
    void inOrderNullTest(Integer value) {
        final BST bst = new BST();
        assertThrows(NullPointerException.class, () -> bst.insert(value));
    }

    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForNextTest")
    @DisplayName("nextNode test")
    void nextNodeTest(TestCaseForSearchBST testCase) {
        bst = buildTree();
        final Node node = bst.search(testCase.value);
        assertThat(testCase.nextValue, is(bst.nextNode(node).getValue()));
    }

    @Test
    @DisplayName("nextNode last element test")
    void nextNodeLastElementTest() {
        bst = buildTree();
        Node node = bst.search(20);
        assertThrows(IllegalArgumentException.class, () -> bst.nextNode(node));
    }

    @ParameterizedTest(name = "{index} test case")
    @NullSource
    @DisplayName("nextNode null test")
    void nextNodeNullTest(Node node) {
        bst = buildTree();
        assertThrows(NullPointerException.class, () -> bst.nextNode(node));
    }

    @DisplayName("search test")
    @ParameterizedTest(name = "{index} test case")
    @ValueSource(ints = {15, 6, 7, 3, 2, 4, 13, 9, 18, 17, 20})
    void searchTest(int value) {
        bst = buildTree();
        assertThat(value, is(bst.search(value).getValue()));
    }

    @DisplayName("search test with duplicated value")
    @Test
    void searchInEmptyTreeTest() {
        final BST bst = new BST();
        assertThrows(NullPointerException.class, () -> bst.search(1));
    }

    @DisplayName("search test with null value")
    @Test
    void searchNullTest() {
        final BST bst = new BST();
        assertThrows(NullPointerException.class, () -> bst.insert(null));
    }

    private ArrayList<Node> convertIntArrayToArrayList(int[] array) {
        return (ArrayList<Node>) Arrays.stream(array).mapToObj(Node::new).collect(Collectors.toList());
    }

    private void fillUpTree(int[] array, BST bst) {
        for (int nodeValue : array) {
            bst.insert(nodeValue);
        }
    }

    private BST buildTree() {
        int[] nodeValues = new int[]{15, 6, 7, 3, 2, 4, 13, 9, 18, 17, 20, 14};
        final BST bst = new BST();
        fillUpTree(nodeValues, bst);
        return bst;
    }
}

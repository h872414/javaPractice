package main.java.datastructures;

import lombok.Value;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
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

    @Value
    private static class TestCaseForBST {
        int[] nodeValues;
        int[] nodeInOrder;
    }

    private static Collection<TestCaseForBST> dataForSimpleTest() {
        return Arrays.asList(
            new TestCaseForBST(new int[]{2, 1, 3}, new int[]{1, 2, 3}),
            new TestCaseForBST(new int[]{5, 4, 6, 3, 7, 2, 8, 1, 9}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
            new TestCaseForBST(new int[]{12, 10, 9, 2, 1, 55}, new int[]{1, 2, 9, 10, 12, 55}),
            new TestCaseForBST(new int[]{500, 200, 300, 1}, new int[]{1, 200, 300, 500})
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
        insertAllValuesIntoBST(testCase.nodeValues, bst);
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

    private ArrayList<Node> convertIntArrayToArrayList(int[] array) {
        return (ArrayList<Node>) Arrays.stream(array).mapToObj(Node::new).collect(Collectors.toList());
    }

    private void insertAllValuesIntoBST(int[] array, BST bst) {
        for (int nodeValue : array) {
            bst.insert(nodeValue);
        }
    }
}

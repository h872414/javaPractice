package main.java.datastructures.heap;

import main.java.datastructures.utils.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class HeapUtilTest {

    Node node35;
    Node node33;
    Node node41;

    @BeforeEach
    void setUp() {
        node35 = new Node(35);
        node33 = new Node(33);
        node41 = new Node(42);
    }

    @Test
    void getNextNodeToAppend_OneNode_Test() {
        assertThat(HeapUtil.getNextNodeToAppend(node41), is(node41));
    }

    @Test
    void getNextNodeToAppend_TwoNode_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        assertThat(HeapUtil.getNextNodeToAppend(node41), is(node41));
    }

    @Test
    void getNextNodeToAppend_ThreeNode_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        node41.setRightChild(node33);
        node35.setParent(node41);
        assertThat(HeapUtil.getNextNodeToAppend(node41), is(node35));
    }

    @Test
    void getLastElementFromTree_OneNode_Test() {
        assertThat(HeapUtil.getLastElementFromTree(node41), is(node41));
    }

    @Test
    void getLastElementFromTree_TwoNode_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        assertThat(HeapUtil.getLastElementFromTree(node41), is(node35));
    }

    @Test
    void getLastElementFromTree_ThreeNode_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        node41.setRightChild(node33);
        node35.setParent(node41);
        assertThat(HeapUtil.getLastElementFromTree(node41), is(node33));
    }

    @Test
    void isNotLeaf_Leaf_Test() {
        assertThat(HeapUtil.isNotLeaf(node33), is(false));
    }

    @Test
    void isNotLeaf_NoLeaf_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        assertThat(HeapUtil.isNotLeaf(node41), is(true));
    }

    @Test
    void hasNodeTwoChildren_Leaf_Test() {
        assertThat(HeapUtil.hasNodeTwoChildren(node33), is(false));
    }

    @Test
    void hasNodeTwoChildren_OneChild_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        assertThat(HeapUtil.hasNodeTwoChildren(node41), is(false));
    }

    @Test
    void hasNodeTwoChildren_TwoChild_Test() {
        node41.setLeftChild(node35);
        node35.setParent(node41);
        node41.setRightChild(node33);
        node35.setParent(node41);
        assertThat(HeapUtil.hasNodeTwoChildren(node41), is(true));
    }

    @Test
    void appendToNode_Test() {
        HeapUtil.appendToNode(node41, 33);
        HeapUtil.appendToNode(node41, 35);
        assert node41.getLeftChild() != null;
        final int leftChildKey = node41.getLeftChild().getKey();
        assert node41.getRightChild() != null;
        final int rightChildKey = node41.getRightChild().getKey();
        assertThat(leftChildKey, is(33));
        assertThat(rightChildKey, is(35));
    }

    @Test
    void isParentGreaterThanLeftChild_True_Test() {
        node33.setParent(node41);
        node41.setLeftChild(node33);
        assertThat(HeapUtil.isParentGreaterThanLeftChild(node33), is(true));
    }

    @Test
    void isParentGreaterThanLeftChild_False_Test() {
        node41.setParent(node33);
        node33.setLeftChild(node41);
        assertThat(HeapUtil.isParentGreaterThanLeftChild(node33), is(false));
    }

    @Test
    void isParentGreaterThanRightChild_True_Test() {
        node33.setParent(node41);
        node41.setRightChild(node33);
        assertThat(HeapUtil.isParentGreaterThanRightChild(node33), is(true));
    }

    @Test
    void isParentGreaterThanRightChild_False_Test() {
        node41.setParent(node33);
        node33.setRightChild(node41);
        assertThat(HeapUtil.isParentGreaterThanRightChild(node33), is(false));
    }

    @Test
    void ifParentIsNotNullSetTargetToChild_NullParent_Test() {
        HeapUtil.ifParentIsNotNullSetTargetToChild(node35, node33);
        assertThat(node33.getParent(), nullValue());
    }

    @Test
    void ifParentIsNotNullSetTargetToChild_NotNullNullParent_Test() {
        node33.setParent(node41);
        node41.setLeftChild(node33);
        HeapUtil.ifParentIsNotNullSetTargetToChild(node33, node35);
        assertThat(node41.getLeftChild(), is(node35));
    }

    @Test
    void isLeftNodeTest() {
        node35.setLeftChild(node33);
        node33.setParent(node35);
        assertThat(HeapUtil.isLeftNode(node33), is(true));
        assertThat(HeapUtil.isLeftNode(node35), is(false));
    }

    @Test
    void isLeftNode_Null_Test() {
        assertThat(HeapUtil.isLeftNode(node33), is(false));
    }

    @Test
    void setParentForRightChildTest() {
        HeapUtil.setParentForRightChild(node35, node33);
        assertThat(node33.getParent(), is(node35));
    }

    @Test
    void setParentForLeftChildTest() {
        HeapUtil.setParentForLeftChild(node35, node33);
        assertThat(node33.getParent(), is(node35));
    }
}

package main.java.datastructures.heap;

import lombok.NonNull;
import main.java.datastructures.utils.Edge;
import main.java.datastructures.utils.Node;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class HeapUtil {

    private HeapUtil() {
    }

    /**
     * Search for the next {@code node} to append a child.
     */

    static @NonNull Node getNextNodeToAppend(final @NonNull Node root) {
        final boolean breakIfExitConditionExists = true;
        return breadthFirstSearch(root, HeapUtil::hasNodeTwoChildren, breakIfExitConditionExists);
    }

    /**
     * Return the last element of a heap.
     */
    static @Nullable Node getLastElementFromTree(final @NonNull Node root) {
        return breadthFirstSearch(root, HeapUtil::isNotLeaf, false);
    }

    /**
     * Conduct breadth-first search algorithm on a tree and continues until exit condition met.
     *
     * @param root             root of the tree.
     * @param isNodeLookingFor evaluate this function if current Node meets the requirements.
     * @param expectBreak      if true, after one {@code Node} meets the requirement breaks the loop and return.ł
     */
    private static Node breadthFirstSearch(
        final Node root,
        final Predicate<Node> isNodeLookingFor,
        final boolean expectBreak
    ) {
        Edge<Node> edge = new Edge<>(root);
        Node possibleLastElement = root;
        while (!edge.isEmpty()) {
            Node node = edge.getNext();
            if (!isNodeLookingFor.test(node)) {
                possibleLastElement = node;
                if (expectBreak) {
                    break;
                }
            } else {
                edgeExpand(edge, node);
            }
        }
        return possibleLastElement;
    }

    /**
     * Create all {@code Node} of the tree, which are available from {@code node}.
     *
     * @param edge current state of the {@code Edge}
     * @param node {@code node} to be expanded
     */
    private static void edgeExpand(@NonNull Edge edge, @NonNull Node node) {
        if (node.getLeftChild() != null) {
            edge.add(node.getLeftChild());
        }
        if (node.getRightChild() != null) {
            edge.add(node.getRightChild());
        }
    }

    /**
     * Return true if {@code node} has no children, otherwise false.
     */
    protected static boolean isNotLeaf(final @NonNull Node node) {
        return !(node.getLeftChild() == null && node.getRightChild() == null);
    }

    /**
     * Return true if {@code node} has two children, otherwise false.
     */
    static boolean hasNodeTwoChildren(final @NonNull Node node) {
        return node.getLeftChild() != null && node.getRightChild() != null;
    }

    /**
     * Append new {@code Node} with the given {@code Integer} {@code value}
     */
    protected static void appendToNode(@NonNull Node nodeToAppend, final @NonNull Integer value) {
        if (nodeToAppend.getLeftChild() == null) {
            appendNewNodeToLeft(nodeToAppend, value);
        } else {
            addendNewNodeToRight(nodeToAppend, value);
        }
    }

    /**
     * Append a new {@code Node} with the value of {@code value} to the left child for the {@code nodeToAppend}.
     */
    private static void appendNewNodeToLeft(final @NonNull Node nodeToAppend, final @NonNull Integer value) {
        Node newNode = new Node(value, nodeToAppend);
        nodeToAppend.setLeftChild(newNode);
    }

    /**
     * Append a new {@code Node} with the value of {@code value} to the right child for the {@code nodeToAppend}.
     */
    private static void addendNewNodeToRight(final @NonNull Node nodeToAppend, final @NonNull Integer value) {
        final @NonNull Node newNode = new Node(value, nodeToAppend);
        nodeToAppend.setRightChild(newNode);
    }

    /**
     * Returns true if parent is greater than its left child.
     */
    static boolean isParentGreaterThanLeftChild(final @NonNull Node parent) {
        return parent.getLeftChild() == null || parent.getKey() >= parent.getLeftChild().getKey();
    }

    /**
     * Returns true if parent is greater than its right child.
     */
    static boolean isParentGreaterThanRightChild(final @NonNull Node parent) {
        return parent.getRightChild() == null || parent.getKey() >= parent.getRightChild().getKey();
    }

    /**
     * Checks if {@code sourceNode} parent is not null, appends {@code targetNode} to parent as its child.
     */
    static void ifParentIsNotNullSetTargetToChild(final @NonNull Node sourceNode, @NonNull Node targetNode) {
        if (sourceNode.getParent() != null) {
            setTargetToChild(sourceNode, targetNode);
        }
    }

    /**
     * Append {@code targetNode} to sourceNode.
     */
    private static void setTargetToChild(final Node sourceNode, final Node targetNode) {
        if (isLeftNode(sourceNode)) {
            assert sourceNode.getParent() != null;
            sourceNode.getParent().setLeftChild(targetNode);
        } else {
            assert sourceNode.getParent() != null;
            sourceNode.getParent().setRightChild(targetNode);
        }
    }

    /**
     * Returns true if {@code node} is a left child.
     */
    static boolean isLeftNode(final @NonNull Node node) {
        if (node.getParent() != null) {
            assert node.getParent().getLeftChild() != null;
            return node.getParent().getLeftChild().equals(node);
        }
        return false;
    }

    /**
     * Set the {@code parent} as a parent of {@code right} node.
     */
    static void setParentForRightChild(final Node parent, final Node right) {
        if (right != null) {
            right.setParent(parent);
        }
    }

    /**
     * Set the {@code parent} as a parent of {@code left} node.
     */
    static void setParentForLeftChild(final Node parent, Node left) {
        if (left != null) {
            left.setParent(parent);
        }
    }
}

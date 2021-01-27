package main.java.datastructures.heap;

import lombok.NonNull;
import main.java.datastructures.utils.Edge;
import main.java.datastructures.utils.Node;
import org.jetbrains.annotations.Nullable;

public class HeapUtil {

    private HeapUtil() {
    }

    /**
     * Search for the next {@code node} to append a child.
     */
    protected static @NonNull Node getNextNodeToAppend(final @NonNull Node root) {
        Edge edge = new Edge(root);
        Node node = root;
        while (!edge.isEmpty()) {
            node = (Node) edge.getNext();
            if (!hasNodeTwoChildren(node)) {
                break;
            }
            edgeExpand(edge, node);
        }
        return node;
    }

    /**
     * Create all {@code Node} of the tree, which are available from {@code node}.
     *
     * @param edge current state of the {@code Edge}
     * @param node {@code node} to be expanded
     */
    private static void edgeExpand(@NonNull Edge edge, @NonNull Node node) {
        if (node.getLeft() != null) {
            edge.add(node.getLeft());
        }
        if (node.getRight() != null) {
            edge.add(node.getRight());
        }
    }

    /**
     * Return the last element of a heap.
     */
    protected static @Nullable Node getLastElementFromTree(final @NonNull Node root) {
        Edge edge = new Edge(root);
        Node possibleLastElement = null;
        while (!edge.isEmpty()) {
            Node node = (Node) edge.getNext();
            if (isLeaf(node)) {
                possibleLastElement = node;
            } else {
                edgeExpand(edge, node);
            }
        }
        return possibleLastElement;
    }

    /**
     * Return true if {@code node} has no children, otherwise false.
     */
    private static boolean isLeaf(final @NonNull Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    /**
     * Return true if {@code node} has two children, otherwise false.
     */
    protected static boolean hasNodeTwoChildren(final @NonNull Node node) {
        return node.getLeft() != null && node.getRight() != null;
    }

    /**
     * Append new {@code Node} with the given {@code Integer} {@code value}
     */
    protected static void appendToNode(@NonNull Node nodeToAppend, final @NonNull Integer value) {
        if (nodeToAppend.getLeft() == null) {
            appendNewNodeToLeft(nodeToAppend, value);
        } else {
            addendNewNodeToRight(nodeToAppend, value);
        }
    }

    private static void appendNewNodeToLeft(@NonNull Node nodeToAppend, final @NonNull Integer value) {
        Node newNode = new Node(value, nodeToAppend);
        nodeToAppend.setLeftChild(newNode);
    }

    private static void addendNewNodeToRight(final @NonNull Node nodeToAppend, final @NonNull Integer value) {
        Node newNode = new Node(value, nodeToAppend);
        nodeToAppend.setRightChild(newNode);
    }
}

package main.java.datastructures;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public class Heap {
    private @Nullable
    Node root;
    Node tmpRoot;
    Node lastElement;
    private int size;

    public Heap() {
        root = null;
        size = 0;
    }

    public @NonNull Node max() {
        if (isRootEmpty()) {
            throw new NullPointerException("Root is empty");
        }
        return (Node) root.clone();
    }

    public void insert(final @NonNull Integer value) {
        if (isRootEmpty()) {
            root = new Node(value);
        } else {
            insertNodeToTheNextEmptyPlace(value);
        }
        size++;
    }

    private boolean isRootEmpty() {
        return root == null;
    }

    private void insertNodeToTheNextEmptyPlace(final @NonNull Integer value) {
        Node nextNodeToAppend = getNextNodeToAppend();
        appendToNode(nextNodeToAppend, value);
        orderTree(nextNodeToAppend);
    }

    private @NonNull Node getNextNodeToAppend() {
        Edge edge = new Edge(root);
        Node node = root;
        while (!edge.isEmpty()) {
            node = (Node) edge.getNext();
            if (!hasNodeTwoChildren(node)) {
                break;
            }
            updateEdge(edge, node);
        }
        return node;
    }

    private boolean hasNodeTwoChildren(final @NonNull Node node) {
        return node.getLeft() != null && node.getRight() != null;
    }

    private void updateEdge(@NonNull Edge edge, @NonNull Node node) {
        if (node.getLeft() != null) {
            edge.add(node.getLeft());
        }
        if (node.getRight() != null) {
            edge.add(node.getRight());
        }
    }

    private void appendToNode(@NonNull Node nodeToAppend, final @NonNull Integer value) {
        if (nodeToAppend.getLeft() == null) {
            appendNewNodeToLeft(nodeToAppend, value);
        } else {
            addendNewNodeToRight(nodeToAppend, value);
        }
    }

    private void appendNewNodeToLeft(@NonNull Node nodeToAppend, final @NonNull Integer value) {
        Node newNode = new Node(value, nodeToAppend);
        nodeToAppend.setLeftChild(newNode);
        if (lastElement != newNode) {
            lastElement = newNode;
        }
    }

    private void addendNewNodeToRight(final @NonNull Node nodeToAppend, final @NonNull Integer value) {
        Node newNode = new Node(value, nodeToAppend);
        nodeToAppend.setRightChild(newNode);
        if (lastElement != newNode) {
            lastElement = newNode;
        }
    }

    private void orderTree(@NonNull Node parent) {
        ifLeftChildIsGreaterThenParentSwapNodes(parent);
        ifRightChildIsGreaterThenParentSwapNodes(parent);
    }

    private void ifLeftChildIsGreaterThenParentSwapNodes(final @NonNull Node parent) {
        if (!isParentGreaterThanLeftChild(parent)) {
            setNodePointers(parent, parent.getLeft());
        }
    }

    private void ifRightChildIsGreaterThenParentSwapNodes(final @NonNull Node parent) {
        if (!isParentGreaterThanRightChild(parent)) {
            setNodePointers(parent, parent.getRight());
        }
    }

    private boolean isParentGreaterThanLeftChild(final @NonNull Node parent) {
        return parent.getLeft() == null || parent.getKey() >= parent.getLeft().getKey();
    }

    private boolean isParentGreaterThanRightChild(final @NonNull Node parent) {
        return parent.getRight() == null || parent.getKey() >= parent.getRight().getKey();
    }

    private void setNodePointers(@NonNull Node sourceNode, @NonNull Node targetNode) {
        final Node tmpNode = (Node) targetNode.clone();
        if (targetNode == lastElement) {
            lastElement = sourceNode;
        }
        ifParentIsNotNullSetTargetToChild(sourceNode, targetNode);
        updateTargetNodeChildren(sourceNode, targetNode);
        updateSourceNodeChildren(sourceNode, tmpNode);
        targetNode.setParent(sourceNode.getParent());
        sourceNode.setParent(targetNode);

        if (targetNode.getParent() != null) {
            orderTree(targetNode.getParent());
        } else {
            root = targetNode;
        }
    }

    private void ifParentIsNotNullSetTargetToChild(final @NonNull Node sourceNode, @NonNull Node targetNode) {
        if (sourceNode.getParent() != null) {
            if (isLeftNode(sourceNode)) {
                sourceNode.getParent().setLeftChild(targetNode);
            } else {
                sourceNode.getParent().setRightChild(targetNode);
            }
        }
    }

    private void updateTargetNodeChildren(final @NonNull Node sourceNode, final @NonNull Node targetNode) {
        if (isLeftNode(targetNode)) {
            updateChildrenAsLeftNode(sourceNode, targetNode);
        } else {
            updateChildrenAdRightNode(sourceNode, targetNode);
        }
    }

    private void updateChildrenAdRightNode(final @NonNull Node sourceNode, final @NonNull Node targetNode) {
        targetNode.setLeftChild(sourceNode.getLeft());
        targetNode.setRightChild(sourceNode);
        setParentForLeftChild(targetNode, targetNode.getLeft());
    }

    private void setParentForLeftChild(final Node parent, Node left) {
        if (left != null) {
            left.setParent(parent);
        }
    }

    private void setParentForRightChild(final Node parent, Node right) {
        if (right != null) {
            right.setParent(parent);
        }
    }

    private void updateChildrenAsLeftNode(final @NonNull Node sourceNode, final @NonNull Node targetNode) {
        targetNode.setLeftChild(sourceNode);
        targetNode.setRightChild(sourceNode.getRight());
        setParentForRightChild(targetNode, targetNode.getRight());
    }

    private void updateSourceNodeChildren(final @NonNull Node sourceNode, final @NonNull Node tmpNode) {
        sourceNode.setLeftChild(tmpNode.getLeft());
        sourceNode.setRightChild(tmpNode.getRight());
        setParentForLeftChild(sourceNode, sourceNode.getLeft());
        setParentForRightChild(sourceNode, sourceNode.getRight());
    }

    private boolean isLeaf(final @NonNull Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private boolean isLeftNode(final @NonNull Node node) {
        return node.getParent().getLeft().equals(node);
    }

    public @NonNull Node popMax() {
        if (root == null) {
            throw new NullPointerException("Heap is empty");
        }
        final Node node = root;
        tmpRoot = root;
        root = null;
        size--;
        if (size > 0) {
            moveLastElementToRoot();
        }
        return node;
    }

    public void moveLastElementToRoot() {
        Node newRoot = (Node) lastElement.clone();
        root = newRoot;
        newRoot.setRightChild(tmpRoot.getRight());
        newRoot.setLeftChild(tmpRoot.getLeft());
        newRoot.setParent(null);
        setParentForLeftChild(lastElement, tmpRoot.getLeft());
        setParentForRightChild(lastElement, tmpRoot.getRight());
        if (isLeftNode(lastElement)) {
            lastElement.getParent().setLeftChild(null);
        } else {
            lastElement.getParent().setRightChild(null);
        }
        lastElement = null;
    }

    public @NonNull Node getLastElement(final @NonNull Node root) {
        Edge edge = new Edge(root);
        Node node = root;
        Node possibleLastElement = null;
        while (!edge.isEmpty()) {
            node = (Node) edge.getNext();
            if (isLeaf(node)) {
                possibleLastElement = node;
            } else {
                updateEdge(edge, node);
            }
        }
        System.out.println(possibleLastElement);
        lastElement = possibleLastElement;
        return lastElement;
    }
}

package main.java.datastructures.heap;

import lombok.NonNull;
import main.java.datastructures.utils.Node;
import org.jetbrains.annotations.Nullable;

import static main.java.datastructures.heap.HeapUtil.*;

public class Heap {
    private @Nullable Node root;
    private @Nullable Node tmpRoot;
    private @Nullable Node lastElement;
    private int size;

    public Heap() {
        root = null;
        size = 0;
    }

    public Node getLastElement() {
        return lastElement;
    }

    public Node getTmpRoot() {
        return tmpRoot;
    }

    public @NonNull Node max() {
        if (root == null) {
            throw new NullPointerException("Root is empty");
        }
        return (Node) root.clone();
    }

    public void insert(final @NonNull Integer value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insertNodeToTheNextEmptyPlace(value);
        }
        size++;
    }

    private void insertNodeToTheNextEmptyPlace(final @NonNull Integer value) {
        Node nextNodeToAppend = getNextNodeToAppend(root);
        appendToNode(nextNodeToAppend, value);
        lastElement = getLastElementFromTree(root);
        orderHeap(nextNodeToAppend);
    }

    private void orderHeap(@NonNull Node parent) {
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
            orderHeap(targetNode.getParent());
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

    private boolean isLeftNode(final @NonNull Node node) {
        return node.getParent().getLeft().equals(node);
    }

    public @NonNull Node popMax() {
        if (size <= 0) {
            throw new NullPointerException("Heap is empty");
        }
        final Node node = root;
        tmpRoot = root;
        root = null;
        size--;
        if (size > 0) {
            moveLastElementToRoot();
            lastElement = getLastElementFromTree(tmpRoot);
        } else {
            tmpRoot = null;
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
    }
}

package main.java.datastructures.heap;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import main.java.datastructures.utils.Node;
import org.jetbrains.annotations.Nullable;

import static main.java.datastructures.heap.HeapUtil.*;

/**
 * Basic Heap data structure.
 * <p>
 * Provide a quick retrieve of the max element.
 *
 * @see <a href="https://www.tutorialspoint.com/data_structures_algorithms/heap_data_structure.htm">Heap</a>
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Heap {
    @Nullable Node root;
    @Nullable Node tmpRoot;
    @Nullable Node lastElement;
    int size;

    public Heap() {
        root = null;
        size = 0;
    }

    /**
     * Get last Node inserted into the {@code Heap}.
     */
    protected @Nullable Node getLastElement() {
        return lastElement;
    }

    /**
     * Returns a copy of the max element.
     */
    protected @NonNull Node max() {
        ifHeapIsEmptyThrowsNullPointerException();
        return root.copyNode();
    }

    /**
     * Insert a {@code Node} given {@code value} into the {@code Heap}.
     */
    public void insert(final @NonNull Integer value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insertNodeToTheNextEmptyPlace(value);
        }
        size++;
    }

    /**
     * Insert {@code value} to the next empty space in the heap.
     * <p>
     * After insertion calls the {@link #orderHeap(Node)} method to restore heap principles
     */
    private void insertNodeToTheNextEmptyPlace(final @NonNull Integer value) {
        Node nextNodeToAppend = getNextNodeToAppend(root);
        appendToNode(nextNodeToAppend, value);
        lastElement = getLastElementFromTree(root);
        orderHeap(nextNodeToAppend);
    }

    /**
     * Restore heap principle.
     * <p>
     * Heap principle: <i>Every Node's key is greater or equal, than the key of it's children.</i>
     */
    private void orderHeap(@NonNull Node parent) {
        ifLeftChildIsGreaterThenParentSwapNodes(parent);
        ifRightChildIsGreaterThenParentSwapNodes(parent);
    }

    private void ifLeftChildIsGreaterThenParentSwapNodes(final @NonNull Node parent) {
        if (!isParentGreaterThanLeftChild(parent)) {
            setNodePointers(parent, parent.getLeftChild());
        }
    }

    private void ifRightChildIsGreaterThenParentSwapNodes(final @NonNull Node parent) {
        if (!isParentGreaterThanRightChild(parent)) {
            setNodePointers(parent, parent.getRightChild());
        }
    }

    /**
     * Swap nodes and updates the corresponding pointers(own, parent's).
     */
    private void setNodePointers(final @NonNull Node sourceNode, final @NonNull Node targetNode) {
        final Node tmpNode = targetNode.copyNode(); //cache targetNode for later use
        updateLastElement(sourceNode, targetNode);
        ifParentIsNotNullSetTargetToChild(sourceNode, targetNode);
        updateTargetNodeChildren(sourceNode, targetNode);
        updateNewChildChildren(sourceNode, tmpNode);
        targetNode.setParent(sourceNode.getParent());
        sourceNode.setParent(targetNode);

        ifHeapIsNotReachedCallRecursion(targetNode);
    }

    private void ifHeapIsNotReachedCallRecursion(final Node targetNode) {
        if (targetNode.getParent() != null) {
            orderHeap(targetNode.getParent());
        } else {
            root = targetNode;
        }
    }

    /**
     * Updates the {@code lastElement} value in the heap.
     */
    private void updateLastElement(final Node sourceNode, final Node targetNode) {
        if (targetNode == lastElement) {
            lastElement = sourceNode;
        }
    }

    /**
     * Swap the two nodes pointers.
     */
    private void updateTargetNodeChildren(final @NonNull Node newChild, final @NonNull Node newParent) {
        if (isLeftNode(newParent)) {
            updateChildrenAsLeftNode(newChild, newParent);
        } else {
            updateChildrenAsRightNode(newChild, newParent);
        }
    }

    /**
     * Updates the children of {@code newParent} Node.
     *
     * @param newRightChild {@code targetNode}'s new right child.
     * @param newParent     new parent.
     */
    private void updateChildrenAsRightNode(final @NonNull Node newRightChild, final @NonNull Node newParent) {
        newParent.setLeftChild(newRightChild.getLeftChild());
        newParent.setRightChild(newRightChild);
        setParentForLeftChild(newParent, newParent.getLeftChild());
    }

    /**
     * Updates the children of {@code newParent} Node.
     *
     * @param newLeftChild {@code targetNode}'s new right child.
     * @param newParent    new newParent.
     */
    private void updateChildrenAsLeftNode(final @NonNull Node newLeftChild, final @NonNull Node newParent) {
        newParent.setLeftChild(newLeftChild);
        newParent.setRightChild(newLeftChild.getRightChild());
        setParentForRightChild(newParent, newParent.getRightChild());
    }

    private void updateNewChildChildren(final @NonNull Node newChild, final @NonNull Node tmpNode) {
        newChild.setLeftChild(tmpNode.getLeftChild());
        newChild.setRightChild(tmpNode.getRightChild());
        setParentForLeftChild(newChild, newChild.getLeftChild());
        setParentForRightChild(newChild, newChild.getRightChild());
    }

    /**
     * Returns the maximum {@code Node} of the {@code Heap}.
     * <p>
     * After pop, removes the max element from the heap, and order the heap.
     */
    public @NonNull Node popMax() {
        ifHeapIsEmptyThrowsNullPointerException();
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

    private void ifHeapIsEmptyThrowsNullPointerException() {
        if (size <= 0) {
            throw new NullPointerException("Heap is empty");
        }
    }

    protected void moveLastElementToRoot() {
        Node newRoot = lastElement.copyNode();
        root = newRoot;
        newRoot.setRightChild(tmpRoot.getRightChild());
        newRoot.setLeftChild(tmpRoot.getLeftChild());
        newRoot.setParent(null);
        setParentForLeftChild(lastElement, tmpRoot.getLeftChild());
        setParentForRightChild(lastElement, tmpRoot.getRightChild());
        if (isLeftNode(lastElement)) {
            lastElement.getParent().setLeftChild(null);
        } else {
            lastElement.getParent().setRightChild(null);
        }
    }
}

package main.java.datastructures.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

import static java.lang.String.format;

/**
 * Represents a single node is a three
 *
 * <ul>
 *      <li>{@code key} according to this value is the node is stored in the tree</li>
 *      <li>{@code value} the value that we want to store in the tree</li>
 *      <li>{@code parent} parent element of the node (if {@code null}, then that is a root)</li>
 *      <li>{@code left} left child element of the node</li>
 *      <li>{@code right} right child element of the node</li>
 * </ul>
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Node {
    final int key;
    final @NonNull Object value;
    @Setter(AccessLevel.PUBLIC)
    @Nullable Node parent;
    @Setter(AccessLevel.PUBLIC)
    @Nullable Node leftChild;
    @Setter(AccessLevel.PUBLIC)
    @Nullable Node rightChild;

    public Node(final @NonNull Object value) {
        this(value, null, null, null);
    }

    public Node(final @NonNull Object value, final @Nullable Node parent) {
        this(value, parent, null, null);
    }

    private Node(
        final @NonNull Object value,
        final @Nullable Node parent,
        final @Nullable Node leftChild,
        final @Nullable Node rightChild
    ) {
        this.value = value;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.key = hashCode();
    }

    @Override
    public String toString() {
        String parentStr = parent == null ? "null" : convertNodeToString(getParent());
        String leftStr = leftChild == null ? "null" : convertNodeToString(getLeftChild());
        String rightStr = rightChild == null ? "null" : convertNodeToString(getRightChild());

        return format("Node{ key=%s, parent=%s, left=%s, right=%s}", key, parentStr, leftStr, rightStr);
    }

    private @NonNull String convertNodeToString(final @Nullable Node node) {
        if (node == null) {
            return "null";
        }
        return String.valueOf(node.getKey());
    }

    public @NonNull Node copyNode() {
        return new Node(value, parent, leftChild, rightChild);
    }

    //This must be adjusted according to the element we'd like to store in the dataStructure
    @Override
    public int hashCode() {
        return (int) this.value;
    }

    //This must be adjusted according to the element we'd like to store in the dataStructure
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Node node = (Node) obj;
        return value == node.value;
    }
}

package main.java.datastructures.binarysearchtree;

import lombok.NonNull;
import main.java.datastructures.utils.Node;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a Binary Search Tree
 * <p>
 * In computer science, a binary search tree (BST), also called an ordered or sorted binary tree,
 * is a rooted binary tree whose internal nodes each store a key greater than all the keys in the node's
 * left subtree and less than those in its right subtree. A binary tree is a type of data structure for
 * storing data such as numbers in an organized way. Binary search trees allow binary search for fast lookup,
 * addition and removal of data items, and can be used to implement dynamic sets and lookup tables.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Binary_search_tree">Read more</a>
 * </p>
 * </br>
 * <p>Operations:</p>
 * <ul>
 *     <li>insertion</li>
 *     <li>deletion</li>
 *     <li>lookup</li>
 * </ul>
 */
public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Access tot he tree
     *
     * @return the root of the tree
     */
    public Node getRoot() {
        return (Node) root.copyNode();
    }

    /**
     * Insert a value in the tree
     * <p>
     * A new key is always inserted at the leaf. We start searching a key from the root until we hit a leaf node.
     * Once a leaf node is found, the new node is added as a child of the leaf node.
     * </p>
     *
     * @param value value to be stored in the tree
     */
    public void insert(Integer value) {
        if (root == null) {
            root = new Node(value);
        } else {
            append(root, value);
        }
    }

    /**
     * Append a value to a given node
     * <ul>
     *     <li>If {@code value} equals to the value stored in the {@code Node} "Value is already stored"</li>
     *     <li>If {@code value} less than the value stored in the {@code Node} append to the left.
     *     <li>If {@code value} greater than the value stored in the {@code Node} append to the right.
     * </ul>
     *
     * @param node  node to append
     * @param value value to append
     *
     * @throws IllegalArgumentException if value is already stored
     */
    private void append(Node node, int value) {
        checkIsNodesEquals(node, value);
        if (node.hashCode() > value) {
            appendLeft(node, value);
        } else {
            appendRight(node, value);
        }
    }

    private void checkIsNodesEquals(Node node, int value) {
        if (node.hashCode() == value) {
            throw new IllegalArgumentException("This objects is already stored in the tree");
        }
    }

    private void appendLeft(Node node, int value) {
        if (node.getLeftChild() == null) {
            node.setLeftChild(new Node(value, node));
        } else {
            append(node.getLeftChild(), value);
        }
    }

    private void appendRight(Node node, int value) {
        if (node.getRightChild() == null) {
            node.setRightChild(new Node(value, node));
        } else {
            append(node.getRightChild(), value);
        }
    }

    /**
     * Travers throughout the tree according to the Node keys
     *
     * @return the {@code Collection} of {@code Nodes} stored in the tree
     */
    public Collection<Node> inOrder() {
        ArrayList<Node> nodes = new ArrayList<>();
        return getSubtree(root, nodes);
    }

    /**
     * Gives the number of elements stored in the tree
     *
     * @return return the number of nodes in the tree
     */
    public int size() {
        final ArrayList<Node> nodes = new ArrayList<>();
        return getSubtree(root, nodes).size();
    }

    /**
     * Get the subtree if the given root
     *
     * @param root  root node
     * @param nodes fills up this Collection
     *
     * @return the collection of child nodes of the root
     */
    private ArrayList<Node> getSubtree(final Node root, ArrayList<Node> nodes) {
        if (root != null) {
            getSubtree(root.getLeftChild(), nodes);
            nodes.add((Node) root.copyNode());
            getSubtree(root.getRightChild(), nodes);
        }
        return nodes;
    }

    /**
     * Search for a given value in the search tree
     *
     * @param value searched value
     *
     * @return return the {@code node} which contains the {@code value}
     *
     * @throws IllegalArgumentException if the value is not in the three
     */
    public Node search(final @NonNull Integer value) {
        return (Node) searchForNode(root, value).copyNode();
    }

    private Node searchForNode(final Node root, final Integer value) {
        if (root.getValue() == value) {
            return root;
        }
        return checkNodeCanBeAppended(root, value);
    }

    private Node checkNodeCanBeAppended(final Node node, final Integer value) {
        if ((Integer) node.getValue() > value) {
            checkIfNodeNullThenThrowException(node.getLeftChild());
            return searchForNode(node.getLeftChild(), value);
        } else {
            checkIfNodeNullThenThrowException(node.getRightChild());
            return searchForNode(node.getRightChild(), value);
        }
    }

    private void checkIfNodeNullThenThrowException(final Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Tree does not contains the value");
        }
    }

    /**
     * Gives the previous node(by stored key) in the tree
     *
     * @param node node which previous, we are looking for
     *
     * @return the previous {@code node} by key
     */
    public Node previousNode(final @NonNull Node node) {
        if (node.getLeftChild() == null) {
            return (Node) searchPreviousInParents(node).copyNode();
        }

        return (Node) searchPreviousInRight(node.getLeftChild()).copyNode();
    }

    private Node searchPreviousInParents(final Node child) {
        if (child.getParent() != null && (Integer) child.getParent().getValue() < (Integer) child.getValue()) {
            return child.getParent();
        }
        ifNodeIsRootThrowsIllegalArgumentException(child, "First element of the tree");
        return searchPreviousInParents(child.getParent());
    }

    private Node searchPreviousInRight(final Node node) {
        if (node.getRightChild() == null) {
            return node;
        }
        return searchPreviousInRight(node.getRightChild());
    }

    /**
     * Gives the next node(by stored key) in the tree
     *
     * @param node node which next we are looking for
     *
     * @return the next {@code node} by key
     */
    public Node nextNode(final @NonNull Node node) {
        if (node.getRightChild() == null) {
            return (Node) searchNextInParents(node).copyNode();
        }
        return (Node) searchNextInLeft(node.getRightChild()).copyNode();
    }

    private Node searchNextInParents(final Node child) {
        ifNodeIsRootThrowsIllegalArgumentException(child.getParent(), "Last element of the three");
        if ((Integer) child.getParent().getValue() > (Integer) child.getValue()) {
            return child.getParent();
        }
        return searchNextInParents(child.getParent());
    }

    private void ifNodeIsRootThrowsIllegalArgumentException(final Node node, String msg) {
        if (node.equals(root)) {
            throw new IllegalArgumentException(msg);
        }
    }

    private Node searchNextInLeft(final Node node) {
        if (node.getLeftChild() == null) {
            return node;
        }
        return searchNextInLeft(node.getLeftChild());
    }

    /**
     * Gives the smallest {@code node}
     *
     * @return return the min element(by key) stored in the tree
     */
    public Node min() {
        ifTreeIsEmptyThrowsNewIllegalSateException();
        Node min = root;
        while (min.getLeftChild() != null) {
            min = min.getLeftChild();
        }
        return (Node) min.copyNode();
    }

    private void ifTreeIsEmptyThrowsNewIllegalSateException() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
    }

    /**
     * Delete a given node if exists in the tree.
     *
     * @param value Node value to be deleted
     *
     * @throws IllegalArgumentException if value is not stored in the tree
     */
    public void delete(final Integer value) {
        Node nodeToDelete = ifTreeDoNotHasNodeThrowsIllegalArgumentException(value);
        deleteNode(nodeToDelete);
    }

    private Node ifTreeDoNotHasNodeThrowsIllegalArgumentException(int value) {
        return search(value);
    }

    private void deleteNode(final @NonNull Node node) {
        if (checkIfNodeIsALeaf(node)) {
            deleteNodeWithNoChild(node);
        } else {
            deleteNodeWithChild(node);
        }
    }

    private boolean checkIfNodeIsALeaf(final Node node) {
        return node.getLeftChild() == null && node.getRightChild() == null;
    }

    private void deleteNodeWithNoChild(final Node node) {
        if (node.getParent().getLeftChild().equals(node)) {
            node.getParent().setLeftChild(null);
        } else {
            node.getParent().setRightChild(null);
        }
    }

    private void deleteNodeWithChild(final Node node) {
        if (checkIfNodeHasTwoChildren(node)) {
            deleteNodeWithTwoChildren(node);
        } else {
            deleteNodeWithOneChild(node);
        }
    }

    private boolean checkIfNodeHasTwoChildren(final Node node) {
        return node.getLeftChild() != null && node.getRightChild() != null;
    }

    private void deleteNodeWithOneChild(final Node node) {
        Node child = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
        if (node.getParent().getLeftChild().equals(node)) {
            node.getParent().setLeftChild(child);
        } else {
            node.getParent().setRightChild(child);
        }
    }

    private void deleteNodeWithTwoChildren(final Node node) {
        Node nextNode = nextNode(node);
        appendRightChildrenIfExists(nextNode);
        replaceNode(node, nextNode);
    }

    private void appendRightChildrenIfExists(Node nextNode) {
        if (nextNode.getRightChild() != null) {
            Node rightChildren = nextNode.getRightChild();
            ifReplacedNodeIsLeftChildUpdateParent(nextNode, rightChildren);
            ifReplacedNodeIsRightChildUpdateParent(nextNode, rightChildren);
        }
    }

    private void replaceNode(Node replacedNode, Node newNode) {
        setNewNodeLeftChildFromReplacedNode(replacedNode, newNode);
        removeNewNodeFromOldPlace(newNode);
        if (!root.equals(replacedNode)) {
            newNode.setParent(replacedNode.getParent());
            ifReplacedNodeIsNotRootUpdateParent(replacedNode, newNode);
        }
        ifReplacedNodeIsRootUpdateRoot(replacedNode, newNode);
    }

    private void setNewNodeLeftChildFromReplacedNode(Node replacedNode, Node newNode) {
        newNode.setLeftChild(replacedNode.getLeftChild());
    }

    private void removeNewNodeFromOldPlace(Node newNode) {
        newNode.getParent().setLeftChild(null);
        newNode.setParent(null);
    }

    private void ifReplacedNodeIsRootUpdateRoot(Node oldNode, Node newNode) {
        if (oldNode.equals(root)) {
            newNode.setRightChild(root.getRightChild());
            newNode.getRightChild().setParent(newNode);
            root = newNode;
        }
    }

    private void ifReplacedNodeIsNotRootUpdateParent(final Node replacedNode, final Node newNode) {
        if (replacedNode != root) {
            ifReplacedNodeIsLeftChildUpdateParent(replacedNode, newNode);
            ifReplacedNodeIsRightChildUpdateParent(replacedNode, newNode);
        }
    }

    private void ifReplacedNodeIsLeftChildUpdateParent(Node replacedNode, Node newNode) {
        if (replacedNode.getParent().getLeftChild().equals(replacedNode)) {
            replacedNode.getParent().setLeftChild(newNode);
        }
    }

    private void ifReplacedNodeIsRightChildUpdateParent(Node replacedNode, Node newNode) {
        if (replacedNode.getParent().getRightChild().equals(replacedNode)) {
            replacedNode.getParent().setRightChild(newNode);
        }
    }

    /**
     * Gives the greatest {@code node}
     *
     * @return return the max element(by key) stored in the tree
     */
    public Node max() {
        ifTreeIsEmptyThrowsNewIllegalSateException();
        Node max = root;
        while (max.getRightChild() != null) {
            max = max.getRightChild();
        }
        return (Node) max.copyNode();
    }

    @Override
    public int hashCode() {
        return root.hashCode() * root.getLeftChild().hashCode() * root.getRightChild().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }
}

package main.java.datastructures.utils;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple queue data structure for <b>bBreadth-first search algorithm</b>.
 * <p>
 * It uses FIFO method, when retrieving element from queue
 * <p>
 * Example:
 * <pre>
 *     final Edge edge = new Edge(root);
 *         Node node = root;
 *         while (!edge.isEmpty()) {
 *             node = (Node) edge.getNext();
 *             if (!hasNodeTwoChildren(node)) {
 *                 break;
 *             }
 *             edgeExpand(edge, node);
 *         }
 *         return node;
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first search algorithm</a>
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Edge<E> {
    @NonNull List<E> queue = new ArrayList<>();

    public Edge() {
    }

    public Edge(final @NonNull E element) {
        queue.add(element);
    }

    /**
     * Returns true if {@code Edge} is empty.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Add the item to the Edge.
     */
    public void add(final @NonNull E e) {
        queue.add(e);
    }

    /**
     * Returns the first element from the queue.
     * <p>
     * The returned element will be deleted from queue.
     *
     * @throws NullPointerException if queue is empty
     */
    public E getNext() {
        ifQueueIsEmptyThrowsNullPointerException();
        final @NonNull E nextNode = queue.get(0);
        deleteNextNodeFromQueue();
        return nextNode;
    }

    private void ifQueueIsEmptyThrowsNullPointerException() {
        if (queue.isEmpty()) {
            throw new NullPointerException("Edge is empty");
        }
    }

    private void deleteNextNodeFromQueue() {
        queue.remove(0);
    }
}
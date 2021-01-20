package main.java.datastructures.utils;

import lombok.NonNull;

import java.util.ArrayList;

public class Edge {
    private ArrayList<Object> queue;

    public Edge() {
        queue = new ArrayList<>();
    }

    public Edge(final @NonNull Object element) {
        queue = new ArrayList<>();
        queue.add(element);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void add(final @NonNull Object node) {
        queue.add(node);
    }

    public Object getNext() {
        ifQueueIsEmptyThrowsNullPointerException();
        Object nextNode = queue.get(0);
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
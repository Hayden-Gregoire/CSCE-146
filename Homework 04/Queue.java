/*
 * Written by Hayden Gregoire
 */

public class Queue<T> {
    private Node<T> front, rear; // Pointers to the front and rear of the queue
    
    // Inner Node class for the linked list structure
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    // Adds an element to the end of the queue
    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear != null) rear.next = node;
        rear = node;
        if (front == null) front = rear;
    }

    // Removes and returns the element from the front of the queue
    public T dequeue() {
        if (front == null) return null;
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }
}
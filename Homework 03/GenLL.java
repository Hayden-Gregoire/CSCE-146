/*
Written By Hayden Gregoire
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenLL<T> implements Iterable<T> {
    private class Node {
        T data;
        Node next;

        // Node constructor to initialize data and set next to null
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public GenLL() {
        head = null;
    }

    // Method to add an element to the list
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            // Traverse to the end of the list
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode; // Add new node to the end
        }
    }

    // Method to remove an element from the list
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        // If the head needs to be removed
        if (head.data.equals(data)) {
            head = head.next;
            return true;
        }

        Node current = head;
        Node previous = null;
        // Traverse the list to find the element to remove
        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.next;
        }

        // If the data was not found
        if (current == null) {
            return false;
        }

        // Remove the node
        previous.next = current.next;
        return true;
    }

    // Method to print the list
    public void printList() {
        Node current = head;
        if (current == null) {
            System.out.println("No tasks.");
            return;
        }
        // Traverse the list and print each task
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Getter for the head node (used in iterator)
    public Node getHead() {
        return head;
    }

    // Iterator implementation for the linked list
    @Override
    public Iterator<T> iterator() {
        return new GenLLIterator();
    }

    private class GenLLIterator implements Iterator<T> {
        private Node current;

        public GenLLIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
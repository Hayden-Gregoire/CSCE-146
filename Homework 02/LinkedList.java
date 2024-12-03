/*
 * Written by Hayden Gregiure
 */
import java.io.FileWriter;
import java.io.IOException;

public class LinkedList<T> {
    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void clear() {
        head = null;
    }

    public LinkedList<T> search(String title, String console) {
        LinkedList<T> result = new LinkedList<>();
        Node<T> current = head;
        while (current != null) {
            VideoGame game = (VideoGame) current.data;
            if (matches(game.title, title) && matches(game.console, console)) {
                result.add(current.data);
            }
            current = current.next;
        }
        return result;
    }

    private boolean matches(String value, String pattern) {
        if (pattern.equals("*")) {
            return true;
        }
        return value.toLowerCase().contains(pattern.toLowerCase());
    }

    public void printToConsole() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void printToFile(String filename, boolean append) throws IOException {
        try (FileWriter writer = new FileWriter(filename, append)) {
            Node<T> current = head;
            while (current != null) {
                writer.write(current.data.toString() + "\n");
                current = current.next;
            }
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
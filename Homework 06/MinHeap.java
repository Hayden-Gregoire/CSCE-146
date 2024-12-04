/*
 * Written by Hayden Gregoire
 */

class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    private static final int MAX_CAPACITY = 100;  // Set a max capacity

    @SuppressWarnings("unchecked")
    public MinHeap() {
        heap = (T[]) new Comparable[MAX_CAPACITY];  // Initialize array
        size = 0;
    }

    public void add(T item) {
        if (size >= MAX_CAPACITY) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = item;
        siftUp(size);
        size++;
    }

    public T remove() {
        if (isEmpty()) return null;
        T root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return root;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[index].compareTo(heap[parent]) >= 0) break;
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        int leftChild, rightChild, smallest;
        while (index < size / 2) {  // Only non-leaf nodes need to sift down
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallest = leftChild;

            if (rightChild < size && heap[rightChild].compareTo(heap[leftChild]) < 0)
                smallest = rightChild;

            if (heap[index].compareTo(heap[smallest]) <= 0) break;
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
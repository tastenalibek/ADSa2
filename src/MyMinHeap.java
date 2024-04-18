public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        this.heap = new MyArrayList<>();
    }

    public void add(T item) {
        heap.add(item);
        // Implement the sift up operation to maintain heap property
    }

    public T extractMin() {
        if (heap.size() == 0) throw new IllegalStateException("Heap is empty");

        return null;
    }

    public T peek() {
        if (heap.size() == 0) throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }
}

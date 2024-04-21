public class MyMinHeap<T extends Comparable<T>> {
    MyArrayList<T> heap = new MyArrayList<>(); // Utilizes an ArrayList to manage heap elements

    public MyMinHeap() { // Constructor initializes an empty min heap
    }

    public void empty() { // Clears all elements from the heap
        heap.clear();
    }

    public int size() { // Returns the current number of elements in the heap
        return heap.size();
    }

    public T getMin() { // Retrieves the smallest element without removing it
        return heap.get(0);
    }

    public T extractMin() { // Removes and returns the smallest element, maintains heap properties
        T min = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        siftDown(0);
        return min;
    }


    public void insert(T item) { // Adds a new element and reorders the heap accordingly
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    private void siftDown(int index) { // Moves the element at the index down to its proper place in the heap
        int left = leftChildOf(index);
        int right = rightChildOf(index);
        int smallest = index;
        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private void siftUp(int index) { // Moves the element at the index up to its proper place in the heap
        while (index != 0 && heap.get(parentOf(index)).compareTo(heap.get(index)) > 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private int leftChildOf(int index) { // Calculates the index of the left child
        return 2 * index + 1;
    }

    private int rightChildOf(int index) { // Calculates the index of the right child
        return 2 * index + 2;
    }

    private int parentOf(int index) { // Calculates the index of the parent
        return (index - 1) / 2;
    }

    private void swap(int i, int j) { // Swaps the elements at two specified indices
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}





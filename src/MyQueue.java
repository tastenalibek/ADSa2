public class MyQueue<T extends Comparable<T>> {
    MyArrayList<T> queue = new MyArrayList<>(); // Implements queue functionality using an ArrayList

    public MyQueue() { // Default constructor for initializing the queue
    }

    public T front() { // Retrieves the first element of the queue without removal
        return queue.get(0);
    }

    public T back() { // Retrieves the last element of the queue without removal
        return queue.get(queue.size() - 1);
    }

    public T dequeue() { // Removes and returns the first element of the queue
        T item = queue.get(0);
        queue.remove(0);
        return item;
    }

    public void enqueue(T item) { // Inserts an element at the end of the queue
        queue.add(item);
    }

    public boolean isEmpty() { // Determines whether the queue contains any elements
        return queue.size() == 0;
    }

    public int size() { // Returns the number of elements in the queue
        return queue.size();
    }

    public void clear() { // Removes all elements from the queue
        queue.clear();
    }
}


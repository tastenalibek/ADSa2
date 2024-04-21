import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private Object[] elements; // Stores the elements of the array list
    private int count = 0; // Tracks the number of elements stored
    private static final int INITIAL_CAPACITY = 10; // Default capacity for the array list

    // Constructor for initializing the list with a specific capacity
    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity must not be negative: " + capacity);
        elements = new Object[capacity];
    }

    // Default constructor for the list
    public MyArrayList() {
        this(INITIAL_CAPACITY);
    }

    // Method to ensure there's enough space to add new elements
    private void expandCapacity() {
        Object[] largerArray = new Object[elements.length * 2];
        System.arraycopy(elements, 0, largerArray, 0, elements.length);
        elements = largerArray;
    }

    // Validates if the index is within the bounds of the array list
    private void rangeCheck(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    // Adds an element to the end of the list
    @Override
    public void add(T element) {
        if (count == elements.length) {
            expandCapacity();
        }
        elements[count++] = element;
    }

    // Replaces the element at the given index with the provided element
    @Override
    public void set(int index, T element) {
        rangeCheck(index);
        elements[index] = element;
    }

    // Inserts an element at the given index
    @Override
    public void add(int index, T element) {
        rangeCheck(index);
        if (count == elements.length) {
            expandCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, count - index);
        elements[index] = element;
        count++;
    }

    // Inserts an element at the start of the list
    @Override
    public void addFirst(T element) {
        add(0, element);
    }

    // Appends an element to the end of the list (synonymous with add method)
    @Override
    public void addLast(T element) {
        add(element);
    }

    // Retrieves the element at a specific index
    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    // Retrieves the first element of the list
    @Override
    public T getFirst() {
        if(count == 0){
            throw new NoSuchElementException("List is empty.");
        }
        return (T) elements[0];
    }

    // Retrieves the last element of the list
    @Override
    public T getLast() {
        if(count == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return (T) elements[count - 1];
    }

    // Removes the element at a specific index
    @Override
    public void remove(int index) {
        rangeCheck(index);
        int elementsToMove = count - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsToMove);
        }
        elements[--count] = null;
    }

    // Removes the first element of the list
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Removes the last element of the list
    @Override
    public void removeLast() {
        remove(count - 1);
    }

    // Sorts the list in ascending order using the Comparable interface
    @Override
    public void sort() {
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count-1-i; j++) {
                if (((Comparable)elements[j]).compareTo(elements[j+1]) > 0) {
                    Object temp = elements[j+1];
                    elements[j+1] = elements[j];
                    elements[j] = temp;
                }
            }
        }
    }

    // Finds the index of the first occurrence of a specific element
    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < count; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < count; i++)
                if (object.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    // Finds the index of the last occurrence of a specific element
    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = count - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = count - 1; i >= 0; i--)
                if (object.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    // Checks if a specific element is in the list
    @Override
    public boolean exists(Object object) {
        return indexOf(object) >= 0;
    }

    // Converts the list into an array
    @Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(elements, count);
    }

    // Clears all elements from the list
    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            elements[i] = null;
        }
        count = 0;
    }

    // Returns the number of elements in the list
    @Override
    public int size() {
        return count;
    }

    // Provides an iterator for the list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }
        };
    }
}





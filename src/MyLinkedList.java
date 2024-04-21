import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T>{

    class MyNode<E> {
        E element; // The value stored within the node
        MyNode next; // Reference to the next node in the sequence
        MyNode prev; // Reference to the previous node in the sequence

        public MyNode(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        } // Node constructor for storing a value

        public MyNode(E element, MyNode next, MyNode prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        } // Node constructor for linking to other nodes

        public MyNode() {
            this.element = null;
            this.next = null;
            this.prev = null;
        } // Default node constructor
    } // Node class

    private MyNode head; // Points to the first node in the list
    private MyNode tail; // Points to the last node in the list
    private int count; // Tracks the total number of nodes in the list


    public MyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    } // Constructor with no parameter



    private void checkCap(int index) {
        if(index < 0|| index >= count)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
    }

    @Override
    public void add(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    @Override
    public void set(int index, T item) {
        checkCap(index);
        MyNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.element = item;
    }

    @Override
    public void add(int index, T item) {
        checkCap(index);
        MyNode newNode = new MyNode(item);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        else if (index == count) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        else {
            MyNode current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        count++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        checkCap(index);
        MyNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T) current.element;
    } // Returns the element at the specified position in this list

    @Override
    public T getFirst() {
        return (T) head.element;
    } // Returns the first element in this list

    @Override
    public T getLast() {
        return (T) tail.element;
    }// Returns the last element in this list

    @Override
    public void remove(int index) {
        checkCap(index);
        if (index == 0) {
            head = head.next;
            head.prev = null;
        }
        else if (index == count - 1) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            MyNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        count--;
    } // Removes the element at the specified position in this list

    @Override
    public void removeFirst() {
        remove(0);
    } // Removes the first element from this list

    @Override
    public void removeLast() {
        remove(count - 1);
    } // Removes the last element from this list

    @Override
    public void sort() {
        for (MyNode i = head; i != null; i = i.next) {
            for (MyNode j = i.next; j != null; j = j.next) {
                if (((Comparable)j.element).compareTo(i.element) < 0) {
                    Object current = i.element;
                    i.element = j.element;
                    j.element = current;
                }
            }
        }
    } // Sorts the elements of this list in ascending order




    @Override
    public int indexOf(Object object) {
        MyNode current = head;
        for (int i = 0; i < count; i++) {
            if (current.element.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    } // Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element

    @Override
    public int lastIndexOf(Object object) {
        MyNode current = tail;
        for (int i = count - 1; i >= 0; i--) {
            if (current.element.equals(object)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    } // Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    } // Returns true if this list contains the specified element

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[count];
        MyNode current = head;
        for (int i = 0; i < count; i++) {
            arr[i] = current.element;
            current = current.next;
        }
        return arr;
    } // Returns an array containing all of the elements in this list in proper sequence

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    } // Removes all of the elements from this list

    @Override
    public int size() {
        return count;
    } // Returns the number of elements in this list

    @Override
    public Iterator iterator() {
        return new MyIterator();
    } // Returns an iterator over the elements in this list in proper sequence

    public class MyIterator implements Iterator<T> {
        private MyNode current = head;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public T next() {
            if (hasNext() != true) {
                throw new NoSuchElementException();
            }
            T element = (T) current.element;
            current = current.next;
            index++;
            return element;
        }
    }

}


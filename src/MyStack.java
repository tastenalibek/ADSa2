import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyStack<T> {

    private MyLinkedList<T> stack = new MyLinkedList<>(); // Uses MyLinkedList as the underlying data structure

    public MyStack() { // Default constructor initializes a new stack
    }

    public T top() { // Returns the top element of the stack without removing it
        return stack.getLast();
    }

    public T pop() { // Retrieves and removes the top element of the stack
        T item = stack.getLast();
        stack.removeLast();
        return item;
    }

    public void push(T item) { // Appends an element to the top of the stack
        stack.addLast(item);
    }

    public boolean isEmpty() { // Determines if the stack is empty
        return stack.size() == 0;
    }

    public int size() { // Returns the number of elements in the stack
        return stack.size();
    }

    public void clear() { // Removes all elements from the stack
        stack.clear();
    }
}



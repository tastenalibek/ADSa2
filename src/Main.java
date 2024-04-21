public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();

        // Adding elements
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        myList.add(50);

        printList(myList);

        // Inserting an element at index 2
        myList.add(2, 25);
        printList(myList);

        // Removing the element at index 3
        myList.remove(3);
        printList(myList);

        // Accessing the first and last element
        System.out.println(myList.getFirst());
        System.out.println(myList.getLast());

        // Updating element at index 1
        myList.set(1, 35);
        printList(myList);

        // Sorting the list
        myList.add(5);
        System.out.println();
        printList(myList);
        myList.sort();
        System.out.println();
        printList(myList);

        // Checking existence of an element
        System.out.println( myList.exists(35));

        // Clearing the list
        myList.clear();
        printList(myList);
    }

    // Helper method to print list elements
    private static void printList(MyArrayList<Integer> list) {
        if (list.size() == 0) {
            System.out.println("The list is empty.");
            return;
        }
        for (Integer item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}

import java.util.Iterator;
public class BST<K extends Comparable<K> , V> implements Iterable<K> {
    private class Node{
        private K key;
        private V value;
        private int length = 1;
        private Node left;
        private Node right;
        public Node(K key , V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BST() {
        root = null;
        size = 0;
    }
    private Node root;
    private int size;

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            root.length = 1;
            size++;
            return;
        }

        Node current = root;


        while (true) {
            if (key.compareTo(current.key) < 0) {

                if (current.left != null)
                    current = current.left;

                else {
                    current.left = new Node(key, value);
                    size++;
                    current.length = 1 + (current.right == null ? 0 : current.right.length) + (current.left == null ? 0 : current.left.length);
                    return;
                }

            }

            else if (key.compareTo(current.key) > 0) {

                if (current.right != null)
                    current = current.right;

                else {
                    current.right = new Node(key, value);
                    size++;
                    current.length = 1 + (current.right == null ? 0 : current.right.length) + (current.left == null ? 0 : current.left.length);
                    return;
                }
            }

            else {
                current.value = value;
                return;
            }

        }

    }

    public V get(K key) {
        return get(root , key);
    }

    private V get(Node node , K key) {

        if(node == null) return null;

        while(node != null) {

            if(key.compareTo(node.key) < 0) node = node.left;

            else if(key.compareTo(node.key) > 0) node = node.right;

            else return node.value;

        }

        return null;

    }

    public void delete(K key) {

        if (root == null) return;

        Node current = root;
        Node parent = null;
        Node temp = null;
        boolean ch = false;

        while (current != null && current.key.equals(key) != true) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
                ch = true;
            }
            else {
                current = current.right;
                ch = false;
            }
        }

        if (current == null) return;

        temp = current;

        if (current.left == null) {
            if (current == root)
                root = current.right;

            else if (ch == true)
                parent.left = current.right;

            else
                parent.right = current.right;
        }

        else if (current.right == null) {
            if (current == root)
                root = current.left;

            else if (ch == true)
                parent.left = current.left;

            else
                parent.right = current.left;

        }


        else {

            Node a = current.right;
            Node ap = current;
            while (a.left != null) {
                ap = a;
                a = a.left;
            }

            if (ap != current) {
                ap.left = a.right;
                a.right = current.right;
            }

            if (current == root)
                root = a;

            else if (ch == true)
                parent.left = a;

            else
                parent.right = a;


            a.left = current.left;
        }

        size--;

        while (parent != null) {
            parent.length = 1 + size(parent.right) + size(parent.left);
            parent = findParent(root, parent.key);
        }
    }

    private Node findParent(Node node, K key) {
        Node a = null;
        while (node != null && node.key.equals(key) != true) {
            a = node;
            if (key.compareTo(node.key) < 0)
                node = node.left;

            else
                node = node.right;

        }
        return a;
    }

    public Node getNode(K key) {
        return getNode(root , key);
    }
    public Node getNode(Node node , K key) {
        if(node == null) return null;

        while(node != null) {

            if(key.compareTo(node.key) < 0) node = node.left;

            else if(key.compareTo(node.key) > 0) node = node.right;

            else return node;

        }

        return null;
    }

    public int size(Node node) {
        return node == null ? 0 : node.length;
    }

    public boolean isEmpty() {
        return size(root) == 1;
    }

    public Node getRoot() {
        return root;
    }


    public boolean contains(K key) {
        return get(key) != null;
    }

    public Iterator<K> iterator(){
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        eue<K> queue = new MyQueue<>();
        public BSTIterator() {
            inOrder(root);
        }

        private void inOrder(Node node) {
            if(node == null) return;
            inOrder(node.left);
            queue.enqueue(node.key);
            inOrder(node.right);
        }
        public boolean hasNext() {
            return !queue.isEmpty();
        }
        public K next() {
            return queue.dequeue();
        }

    }



}

public class LinkedListDeque<T> {

    /** Initialization Node. */
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T item) {
            this.prev = null;
            this.item = null;
            this.next = null;
        }

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /** Initialization data. */
    private Node sentinel;
    private int size;

    /** Create an empty Deque. */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /*
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
    */

    /** Add data of the front in the Deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Add data of the last in the Deque. */
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** Return Deque whether it is empty. */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    /** Return the number of size in the Deque. */
    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;

        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }

        System.out.println();
    }

    /** Remove and return the item at the front of the Deque. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = getFirst();
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;

        return res;
    }

    /** Remove and return the item at the last of the Deque. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = getLast();
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;

        return res;
    }

    /** Return the index item of the Deque. using iteration. */
    public T get(int index) {
        Node p = sentinel.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.item;
    }

    /** Return the first item of the Deque. */
    private T getFirst() {
        return sentinel.next.item;
    }

    /** Return the last item of the Deque. */
    private T getLast() {
        return sentinel.prev.item;
    }

    private T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }

        return getRecursive(index - 1, p.next);
    }

    /** Return the index of the Deque. using recursion. */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

}

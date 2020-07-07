public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T item) {
            prev = null;
            this.item = item;
            next = null;
        }

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
    public LinkedListDeque(LinkedListDeque<T> otherListDeque) {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < otherListDeque.size; i++) {
            addLast(otherListDeque.get(i));
        }
    }
    */

    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev,item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;

        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T res = getFirst();
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;

        return res;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T res = getLast();
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return res;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.item;
    }

    private T getFirst() {
        return sentinel.next.item;
    }

    private T getLast() {
        return sentinel.prev.item;
    }

    private T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }

        return getRecursive(index - 1, p.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }
}

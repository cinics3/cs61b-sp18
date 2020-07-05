public class LinkedListDeque<T> {

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

    private Node sentinel;
    private int size;

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

    public void addFirst(T item) {
        Node p = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    public void addLast(T item) {
        Node p = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

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

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T res = getFirst();
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return res;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T res = getLast();
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return res;
        }
    }

    public T get(int index) {
        int cnt = 1;
        Node p = sentinel.next;

        while (p != sentinel) {
            if (index == cnt) {
                return p.item;
            }
            p = p.next;
        }

        return null;
    }

    private T getFirst() {
        return sentinel.next.item;
    }

    private T getLast() {
        return sentinel.prev.item;
    }

    public T getRecursive(int index) {
        if (index == 1) {
            return sentinel.next.item;
        } else {
            sentinel = sentinel.next;

            if (sentinel.next == sentinel) {
                return null;
            } else {
                return getRecursive(index - 1);
            }
        }
    }
}

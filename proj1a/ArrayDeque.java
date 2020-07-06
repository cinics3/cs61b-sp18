public class ArrayDeque<T> {

    /** Initialization data*/
    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static int factor = 2;

    /** Create an empty ArrayDeque. */
    public ArrayDeque() {
        item = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /*
    public ArrayDeque(ArrayDeque other) {
        item = (T[]) new Object[other.item.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        System.arraycopy(item, 0, other, 0, other.item.length);
    }
    */

    private boolean isSparse() {
        return item.length >= 16 && size < (item.length / 4);
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldIdx = (nextFirst + 1 + item.length) % item.length;
        for (int newIdx = 0; newIdx < size; newIdx++) {
            a[newIdx] = item[oldIdx];
            oldIdx = (oldIdx + 1 % item.length) % item.length;
        }
        item = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Add data of the front in the ArrayDeque. */
    public void addFirst(T x) {
        if (isFull()) {
            resize(size * factor);
        }

        item[nextFirst] = x;
        nextFirst = (nextFirst - 1 + item.length) % item.length;
        size += 1;
    }

    /** Add data of the last in the ArrayDeque. */
    public void addLast(T x) {
        if (isFull()) {
            resize(size * factor);
        }

        item[nextLast] = x;
        nextLast = (nextLast + 1 + item.length) % item.length;
        size += 1;
    }

    /** Return ArrayDeque whether it is empty. */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    /** Return ArrayDeque whether it is full. */
    private boolean isFull() {
        return size == item.length;
    }

    /** Return the size of ArrayDeque. */
    public int size() {
        return size;
    }

    /** Print ArrayDeque. */
    public void printDeque() {
        int first = (nextFirst + 1 + item.length) % item.length;
        int cnt = 0;

        while (cnt < size) {
            cnt++;
            System.out.print(item[first] + " ");
            first = (first + 1 + item.length) % item.length;
        }
        System.out.println();
    }

    /** Remove the first item of the ArrayDeque. */
    public T removeFirst() {
        if (isSparse()) {
            resize(item.length / 2);
        }
        if (isEmpty()) {
            return null;
        }

        nextFirst = (nextFirst + 1 + item.length) % item.length;
        T res = getFirst();
        item[nextFirst] = null;
        size -= 1;

        return res;
    }

    /** Remove the last item of the ArrayDeque. */
    public T removeLast() {
        if (isSparse()) {
            resize(item.length / 2);
        }

        if (isEmpty()) {
            return null;
        }

        nextLast = (nextLast - 1 + item.length) % item.length;
        T res = getLast();
        item[nextLast] = null;
        size -= 1;

        return res;
    }

    /** Return the first item of ArrayDeque. */
    private T getFirst() {
        return item[nextFirst];
    }

    /** Return the last item of ArrayDeque. */
    private T getLast() {
        return item[nextLast];
    }

    public T get(int i) {
        if (i >= size) {
            return null;
        }

        int start = (nextFirst + 1 + item.length) % item.length;
        return item[(start + i) % item.length];
    }

}

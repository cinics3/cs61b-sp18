public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static int factor = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    private boolean isFull() {
        return size == items.length;
    }

    private int plusOne(int x) {
        return (x + 1 + items.length) % items.length;
    }

    private int minusOne(int x) {
        return (x - 1 + items.length) % items.length;
    }

    private boolean isSparse() {
        return items.length >= 16 && size < (items.length / 4);
    }

    private void resize(int capacity) {
        T[] res = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);

        for (int newIndex = 0; newIndex < size; newIndex++) {
            res[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }

        items = res;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(items.length * factor);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(items.length * factor);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int start = plusOne(nextFirst), i = 0; i < size; i++) {
            System.out.print(items[start] + " ");
            start = plusOne(start);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isSparse()) {
            resize(items.length / factor);
        }

        if (isEmpty()) {
            return null;
        }

        nextFirst = plusOne(nextFirst);
        T res = getFirst();
        items[nextFirst] = null;
        size -= 1;

        return res;
    }

    @Override
    public T removeLast() {
        if (isSparse()) {
            resize(items.length / factor);
        }

        if (isEmpty()) {
            return null;
        }

        nextLast = minusOne(nextLast);
        T res = getLast();
        items[nextLast] = null;
        size -= 1;

        return res;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }

    private T getFirst() {
        return items[nextFirst];
    }

    private T getLast() {
        return items[nextLast];
    }
}

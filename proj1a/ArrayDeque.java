public class ArrayDeque<T> {

    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int maxSize;
    private int size;
    private static int factor = 2;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        maxSize = 8;
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        item = (T[]) new Object[other.maxSize];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        maxSize = other.maxSize;
        size = other.size;
        System.arraycopy(item, 0, other, 0, maxSize - 1);
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(item, 0, a, 0, maxSize - 1);
        maxSize = capacity;
        item = a;
    }

    public void addFirst(T x) {
        if (size == maxSize) {
            resize(maxSize * factor);
        }

        while (getFirst() != null) {
            nextFirst = (nextFirst - 1 + maxSize) % maxSize;
        }
        item[nextFirst] = x;
        size += 1;
    }

    public void addLast(T x) {
        if (size == maxSize) {
            resize(maxSize * factor);
        }

        while (getLast() != null) {
            nextLast = (nextLast + 1 + maxSize) % maxSize;
        }
        item[nextLast] = x;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int first = nextFirst;
        int cnt = 0;

        while (cnt <= size) {
            cnt++;
            System.out.println(item[first] + " ");
            first = (first + 1 + maxSize) % maxSize;
        }
        System.out.println();
    }

    public T removeFirst() {
        T res = getFirst();
        item[nextFirst] = null;
        nextFirst = (nextFirst + 1 + maxSize) % maxSize;
        size -= 1;
        return res;
    }

    public T removeLast() {
        T res = getLast();
        item[nextLast] = null;
        nextLast = (nextLast - 1 + maxSize) % maxSize;
        size -= 1;
        return res;
    }

    private T getFirst() {
        return item[nextFirst];
    }


    private T getLast() {
        return item[nextLast];
    }

    public T get(int i) {
        return item[i];
    }

}

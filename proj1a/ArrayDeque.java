public class ArrayDeque<T> {

    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int MaxSize;
    private int size;
    private static int factor = 2;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        MaxSize = 8;
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        item = (T[]) new Object[other.MaxSize];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        MaxSize = other.MaxSize;
        size = other.size;
        System.arraycopy(item, 0, other, 0, MaxSize - 1);
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(item, 0, a, 0, MaxSize - 1);
        MaxSize = capacity;
        item = a;
    }

    public void addFirst(T x) {
        if(size == MaxSize) {
            resize(MaxSize * factor);
        }

        while(getFirst() != null) {
            nextFirst = (nextFirst - 1 + MaxSize) % MaxSize;
        }
        item[nextFirst] = x;
        size += 1;
    }

    public void addLast(T x) {
        if(size == MaxSize) {
            resize(MaxSize * factor);
        }

        while(getLast() != null) {
            nextLast = (nextLast + 1 + MaxSize) % MaxSize;
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

        while(cnt <= size) {
            cnt ++;
            System.out.println(item[first] + " ");
            first = (first + 1 + MaxSize) % MaxSize;
        }
        System.out.println();
    }

    public T removeFirst() {
        T res = getFirst();
        item[nextFirst] = null;
        nextFirst = (nextFirst + 1 + MaxSize) % MaxSize;
        size -= 1;
        return res;
    }

    public T removeLast() {
        T res = getLast();
        item[nextLast] = null;
        nextLast = (nextLast - 1 + MaxSize) % MaxSize;
        size -= 1;
        return res;
    }

    public T getFirst() {
        return item[nextFirst];
    }


    public T getLast() {
        return item[nextLast];
    }

    public T get(int i) {
        return item[i];
    }

}

public class ArrayDeque<T> {
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;

    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        items = (T[]) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, other.size);
    }

    private void resize(int capacity) {
        T[] previousItems = items;
        items = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            nextFirst = (nextFirst + 1) % previousItems.length;
            items[i] = previousItems[nextFirst];
        }
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size + 1 > items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % items.length;
        size++;
    }

    public void addLast(T item) {
        if (size + 1 > items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = (nextFirst + 1) % items.length;
             (i - nextFirst - 1) % items.length < size;
             i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.printf("\n");
    }

    public T removeFirst() {
        assert isEmpty();
        if (size - 1 < items.length / 4) {
            resize(items.length / 2);
        }
        T temp = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        size--;
        return temp;
    }

    public T removeLast() {
        assert isEmpty();
        if (size - 1 < items.length / 4) {
            resize(items.length / 2);
        }
        T temp = items[(nextLast - 1) % items.length];
        items[(nextLast - 1) % items.length] = null;
        size--;
        return temp;
    }

    public T get(int index) {
        assert index < size;
        return items[(index + nextFirst + 1) % items.length];
    }
}

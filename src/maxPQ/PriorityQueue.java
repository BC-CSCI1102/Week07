public interface PriorityQueue<T extends Comparable<T>> {
    void enqueue(T item);

    T dequeue();

    boolean isEmpty();

    int size();

    String toString();
}



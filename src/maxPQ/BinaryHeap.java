public interface BinaryHeap<T extends Comparable<T>> {

  void add(T item);

  T removeMax();

  boolean isEmpty();

  int size();

  String toString();

}

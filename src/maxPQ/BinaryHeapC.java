import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeapC<T extends Comparable<T>> implements BinaryHeap<T> {

  private static final int CAPACITY = 32;
  private T[] a;
  private int n;
  private Comparator<T> comparator;

  public BinaryHeapC() {
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[CAPACITY];
    a = temp;
    n = 0;
  }

  public BinaryHeapC(T[] items) {
    T[] temp = (T[]) new Comparable[CAPACITY];
    a = temp;
    n = items.length;
    for (int i = 0; i < n; i++)
      a[i + 1] = items[i];
    build();
  }

  private boolean less(int i, int j) {
    if (comparator == null)
      return ((Comparable<T>) a[i]).compareTo(a[j]) < 0;
    else
      return comparator.compare(a[i], a[j]) < 0;
  }

  private void exch(int i, int j) {
    T swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private void sink(int k) {
    while (2 * k <= n) {
      int j = 2 * k;
      if (j < n && less(j, j + 1)) j++;
      if (!less(k, j)) break;
      exch(k, j);
      k = j;
    }
  }

  // resize the underlying array to have the given capacity
  private void resize(int capacity) {
    assert capacity > n;
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[capacity];
    for (int i = 1; i <= n; i++)
      temp[i] = a[i];
    a = temp;
  }

  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return n == 0;
  }


  private void build() {
    for (int i = n / 2; i > 0; i--)
      sink(i);
  }

  public void add(T key) {
    if (n == a.length - 1) resize(2 * a.length);
    a[++n] = key;
    swim(n);
  }

  public T removeMax() {
    if (n == 0)
      throw new NoSuchElementException("Binary Heap underflow");
    T result = a[1];
    a[1] = a[n--];
    sink(1);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++)
      sb.append(String.format("%s ", a[i].toString()));
    return String.format("[ %s]", sb.toString());
  }

  // Unit testing
  public static void main(String[] args) {

    String[] items = {
            "Iowa", "Ohio", "Colorado", "Utah",
            "Oregon", "Georgia", "Nevada", "Maine"
    };

    BinaryHeap<String> heap = new BinaryHeapC<String>(items);

    while (!heap.isEmpty())
      System.out.format("%s\n", heap.removeMax());
  }
}

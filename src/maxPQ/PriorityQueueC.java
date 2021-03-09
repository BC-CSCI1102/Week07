public class PriorityQueueC<T extends Comparable<T>>
  implements PriorityQueue<T> {

  private BinaryHeap<T> heap;

  public PriorityQueueC() {
    heap = new BinaryHeapC();
  }
  
  public PriorityQueueC(T[] items) {
    heap = new BinaryHeapC(items);
  }

  public void enqueue(T item) {
    heap.add(item);
  }

  public T dequeue() {
    return heap.removeMax();
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }

  public int size() {
    return heap.size();
  }
  
  public String toString() {
    return heap.toString();
  }

  public static void main(String[] args) {

    Integer[] items = {3, 1, 2, 4};
    PriorityQueue<Integer> pq = new PriorityQueueC<Integer>(items);

    while (!pq.isEmpty())
      System.out.format("%s\n", pq.dequeue().toString());
  }
}

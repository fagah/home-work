import java.util.ArrayList;
import java.util.PriorityQueue;

public class PriorityQueue3<E extends Comparable<E>> implements PriorityQueueIF<E> {
    private PriorityQueue<E> priorityQueue;

    public PriorityQueue3() {
        priorityQueue = new PriorityQueue<>();
    }

    public PriorityQueue3(int initialCapacity) {
        priorityQueue = new PriorityQueue<>(initialCapacity);
    }

    public PriorityQueue3(ArrayList<E> elements, int initialCapacity) {
        priorityQueue = new PriorityQueue<>(initialCapacity);
        priorityQueue.addAll(elements);
    }

    @Override
    public boolean offer(E e) {
        return priorityQueue.offer(e);
    }

    @Override
    public E poll() {
        return priorityQueue.poll();
    }

    @Override
    public E peek() {
        return priorityQueue.peek();
    }

    @Override
    public int size() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }
}

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue1<E extends Comparable<E>> implements PriorityQueueIF<E> {
    private List<E> elements;
    private int k;

    public PriorityQueue1(int capacity) {
        this.k = capacity;
        elements = new ArrayList<>(capacity);
    }

    public PriorityQueue1(ArrayList<E> list, int capacity) {
        this.k = capacity;
        elements = new ArrayList<>(capacity);
        for (E item : list) {
            offer(item);
        }
    }

    public void setK(int capacity) {
        this.k = capacity;
        // Ensure the capacity is not less than the current size
        if (capacity < elements.size()) {
            elements.subList(capacity, elements.size()).clear();
        }
    }

    @Override
    public boolean offer(E e) {
        if (elements.size() < k) {
            elements.add(e);
            return true;
        }

        E max = e;
        int maxIndex = -1;
        for (int i = 0; i < elements.size(); i++) {
            E current = elements.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
                maxIndex = i;
            }
        }

        if (maxIndex != -1) {
            elements.set(maxIndex, e);
            return true;
        }

        return false;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E max = elements.get(0);
        int maxIndex = 0;

        for (int i = 1; i < elements.size(); i++) {
            E current = elements.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
                maxIndex = i;
            }
        }

        elements.remove(maxIndex);
        return max;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        E max = elements.get(0);

        for (int i = 1; i < elements.size(); i++) {
            E current = elements.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }

        return max;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}

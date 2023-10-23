import java.util.ArrayList;

public class PriorityQueue2<E extends Comparable<E>> implements PriorityQueueIF<E> {
    private ArrayList<E> elements;
    private int capacity;

    public PriorityQueue2(int capacity) {
        this.capacity = capacity;
        elements = new ArrayList<>(capacity + 1);
        elements.add(null); // The first element (index 0) is not used for simplicity.
    }

    @Override
    public boolean offer(E e) {
        if (elements.size() <= capacity) {
            elements.add(e);
            upHeap(elements.size() - 1);
        } else {
            E max = peek();
            if (e.compareTo(max) < 0) {
                poll();
                elements.add(e);
                upHeap(elements.size() - 1);
            }
        }
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        E root = elements.get(1);
        E last = elements.remove(elements.size() - 1);

        if (!isEmpty()) {
            elements.set(1, last);
            downHeap(1);
        }

        return root;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements.get(1);
    }

    @Override
    public int size() {
        return elements.size() - 1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void upHeap(int index) {
        E e = elements.get(index);
        while (index > 1) {
            int parentIndex = index / 2;
            E parent = elements.get(parentIndex);
            if (e.compareTo(parent) > 0) {
                elements.set(index, parent);
                index = parentIndex;
            } else {
                break;
            }
        }
        elements.set(index, e);
    }

    private void downHeap(int index) {
        E e = elements.get(index);
        int childIndex;
        while (index * 2 <= size()) {
            childIndex = index * 2;
            E child = elements.get(childIndex);
            if (childIndex < size()) {
                E nextChild = elements.get(childIndex + 1);
                if (nextChild.compareTo(child) > 0) {
                    childIndex++;
                    child = nextChild;
                }
            }
            if (e.compareTo(child) < 0) {
                elements.set(index, child);
                index = childIndex;
            } else {
                break;
            }
        }
        elements.set(index, e);
    }
}

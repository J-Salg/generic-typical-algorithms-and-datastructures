package data_structure.tree.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeapTree<T> {

    private final List<HeapNode<T>> heap;
    private final Comparator<? super T> comparator;

    public BinaryHeapTree(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public BinaryHeapTree(List<T> elements, Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
        for (T value : elements) {
            this.heap.add(new HeapNode<>(value));
        }
        buildHeap();
    }

    public void insert(T value) {
        heap.add(new HeapNode<>(value));
        heapifyUp(heap.size() - 1);
    }

    public T extractRoot() {
        if (heap.isEmpty()) return null;

        T rootValue = heap.getFirst().getValue();
        HeapNode<T> last = heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return rootValue;
    }

    public T peekRoot() {
        return heap.isEmpty() ? null : heap.getFirst().getValue();
    }

    private void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            T currentValue = heap.get(index).getValue();
            T parentValue = heap.get(parent).getValue();

            if (comparator.compare(currentValue, parentValue) >= 0) break;

            swap(index, parent);
            index = parent;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && comparator.compare(heap.get(left).getValue(), heap.get(smallest).getValue()) < 0) {
                smallest = left;
            }

            if (right < size && comparator.compare(heap.get(right).getValue(), heap.get(smallest).getValue()) < 0) {
                smallest = right;
            }

            if (smallest == index) break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        HeapNode<T> tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void printHeap() {
        System.out.print("Heap: ");
        for (HeapNode<T> node : heap) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
    }
}

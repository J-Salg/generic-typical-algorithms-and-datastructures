package algorithms.sort;

public class HeapSort {

    public static <T extends Comparable<? super T>> void heapSort(T[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapifyDown(arr, i, 0);
        }
    }

    private static <T extends Comparable<? super T>> void heapifyDown(T[] arr, int size, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < size && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != index) {
            swap(arr, index, largest);
            heapifyDown(arr, size, largest);
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
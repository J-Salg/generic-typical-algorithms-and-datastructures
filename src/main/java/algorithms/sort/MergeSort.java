package algorithms.sort;

/**
 * Implements the <b>MergeSort Algorithm</b> for generic arrays.
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(N log N)</li>
 *     <li><b>Average Case:</b> O(N log N)</li>
 *     <li><b>Worst Case:</b> O(N log N)</li>
 * </ul>
 */
public class MergeSort {

    private MergeSort(){}

    /**
     * Sorts a generic array using the Merge Sort algorithm in ascending order.
     *
     * @param array the array to sort
     * @param <T>   the element type, must implement {@link Comparable}
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array){
        if(array == null || array.length < 2){
            return;
        }

        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[array.length];
        mergeSort(array, aux, 0, array.length - 1);
    }

    /**
     * Recursively divides the array into halves until subarrays of size 1 are reached.
     * Then merges the sorted halves using the {@link #merge(T[], T[], int, int, int)} method.
     *
     * @param array the array to sort
     * @param aux   the auxiliary array used for merging
     * @param left  the leftmost index of the current segment
     * @param right the rightmost index of the current segment
     * @param <T>   the element type, must implement {@link Comparable}
     */
    private static <T extends Comparable<T>> void mergeSort(T[] array, T[] aux, int left, int right){
        if(left >= right){
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(array, aux, left, mid);
        mergeSort(array, aux, mid + 1, right);

        merge(array, aux, left, mid, right);
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] aux, int left, int mid, int right){
        for(int i = left; i <= right; i++){
            aux[i] = array[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (aux[i].compareTo(aux[j]) <= 0) {
                array[k++] = aux[i++];
            } else {
                array[k++] = aux[j++];
            }
        }

        while (i <= mid){
            array[k++] = aux[i++];
        }
    }

}

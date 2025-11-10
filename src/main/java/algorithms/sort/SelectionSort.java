package algorithms.sort;

/**
 * Implements the <b>Selection Sort Algorithm</b>.
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(N)</li>
 *     <li><b>Average Case:</b> O(N²)</li>
 *     <li><b>Worst Case:</b> O(N²)</li>
 * </ul>
 */
public class SelectionSort {

    private SelectionSort(){}

    /**
     * Sorts a generic array using the Selection Sort algorithm in ascending order.
     *
     * @param array the array to sort
     * @param <T>   the element type, must implement {@link Comparable}
     */
    public static <T extends Comparable<T>> void selectionSort(T[] array){

        if(array == null || array.length == 0){
            return;
        }

        int size = array.length;

        int minIndex = 0;
        for(int i = 0; i < size - 1; i++){
            minIndex = i;
            for(int j = i + 1; j < size; j++){
                if(array[minIndex].compareTo(array[j]) > 0){
                    minIndex = j;
                }
            }

            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

package algorithms.sort;

/**
 * Implements the <b>Insertion Sort Algorithm</b>.
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(N)</li>
 *     <li><b>Average Case:</b> O(N²)</li>
 *     <li><b>Worst Case:</b> O(N²)</li>
 * </ul>
 */
public class InsertionSort {

    private InsertionSort(){}


    /**
     * Sorts a generic array using the Insertion Sort algorithm in ascending order.
     *
     * @param array the array to sort
     * @param <T>   the element type, must implement {@link Comparable}
     */
    public static <T extends Comparable<T>> void insertionSort(T[] array){

        if(array == null){
            return;
        }

        int size = array.length;

        for(int i = 1; i < size; i++){
            T key = array[i];

            int j = i - 1;
            while(j >= 0 && array[j].compareTo(key) > 0){
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }

    }

}

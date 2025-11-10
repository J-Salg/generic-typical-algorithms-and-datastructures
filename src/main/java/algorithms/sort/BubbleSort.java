package algorithms.sort;

/**
 * Implements the <b>Bubble Sort Algorithm</b>.
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(N)</li>
 *     <li><b>Average Case:</b> O(N²)</li>
 *     <li><b>Worst Case:</b> O(N²)</li>
 * </ul>
 */
public class BubbleSort {

    private BubbleSort(){}

    /**
     * Sorts a generic array using the Bubble Sort algorithm in ascending order.
     *
     * @param array the array to sort
     * @param <T>   the element type, must implement {@link Comparable}
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] array){

        if(array == null || array.length == 0){
            return;
        }

        int size = array.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size - i - 1; j++){
                if(array[j].compareTo(array[j + 1]) > 0){
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

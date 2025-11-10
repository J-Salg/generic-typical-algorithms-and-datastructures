package algorithms.searching;

/**
 * Implements the <b>Linear Search Algorithm</b>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(1)</li>
 *     <li><b>Average Case:</b> O(N)</li>
 *     <li><b>Worst Case:</b> O(N)</li>
 * </ul>
 * */
public class LinearSearch {

    private LinearSearch(){}

    /**
     * Performs a linear search in a generic array.
     *
     * @param array the array to search for (can contain null values)
     * @param target the element to search for (can be null)
     * @param <T> the data type of the array elements
     * @return the index of the element found, or -1 if not found
     * */
    public static <T> int linearSearch(T[] array, T target){

        for(int i = 0; i < array.length; i++){
            if(array[i] != null && array[i].equals(target)){
                return i;
            } else if (array[i] == null && target == null) {
                return i;
            }
        }

        return -1;

    }

}

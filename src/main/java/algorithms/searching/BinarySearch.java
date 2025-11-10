package algorithms.searching;

/**
 * Implements the <b>Binary Search Algorithm</b> (Recursive and Iterative versions).
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(1)</li>
 *     <li><b>Average Case:</b> O(log(N))</li>
 *     <li><b>Worst Case:</b> O(log(N))</li>
 * </ul>
 * */
public class BinarySearch {

    private BinarySearch(){}

    // ##################
    // Iterative Version
    // ##################
    /**
     * Performs an iterative binary search on a sorted generic array.
     *
     * @param array  the sorted array to search
     * @param target the element to find
     * @param <T>    the type of elements
     * @return the index of the target if found, otherwise -1
     * */
    public static <T extends Comparable<T>> int iterativeBinarySearch(T[] array, T target){
        if(array == null || array.length == 0){
            return -1;
        }

        int min_index = 0;
        int max_index = array.length - 1;

        while (min_index <= max_index){
            int mid = min_index + (max_index - min_index) / 2;

            int cmp = array[mid].compareTo(target);
            if(cmp == 0){
                return mid;
            } else if (cmp < 0) {
                min_index = mid + 1;
            }else {
                max_index = mid - 1;
            }
        }

        return -1;
    }


    // ##################
    // Recursive Version
    // ##################
    /**
     * Performs a recursive binary search on a sorted generic array.
     *
     * @param array  the sorted array to search
     * @param target the element to find
     * @param <T>    the type of elements
     * @return the index of the target if found, otherwise -1
     * */
    public static <T extends Comparable<T>> int recursiveBinarySearch(T[] array, T target){
        if(array == null || array.length == 0){
            return -1;
        }

        return recursiveBinarySearch(array, target, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int recursiveBinarySearch(
            T[] array,
            T target,
            int min_index,
            int max_index)
    {

        if(min_index <= max_index){
            int mid = min_index + (max_index - min_index)/2;

            int cmp = array[mid].compareTo(target);
            if(cmp == 0){
                return mid;
            } else if (cmp < 0) {
                return recursiveBinarySearch(array, target, mid + 1, max_index);
            }else {
                return recursiveBinarySearch(array, target, min_index, mid - 1);
            }
        }

        return -1;
    }

}

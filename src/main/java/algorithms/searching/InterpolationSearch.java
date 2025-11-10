package algorithms.searching;

/**
 * Implements the <b>Interpolation Search Algorithm</b> (Recursive and Iterative versions).
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(1)</li>
 *     <li><b>Average Case:</b> O(log(log(N)))</li>
 *     <li><b>Worst Case:</b> O(N)</li>
 * </ul>
 *
 * <p>Note: The array must be <b>sorted</b> in ascending order and contain <b>numeric</b> values
 * with approximately <b>uniform distribution</b> for optimal performance.</p>
 */
public class InterpolationSearch {

    private InterpolationSearch(){}

    // #################
    // Iterative Version
    // #################
    /**
     * Performs an iterative interpolation search on a sorted numeric array.
     *
     * @param array  the sorted array to search
     * @param target the numeric value to find
     * @param <T>    a type extending {@link Number} and {@link Comparable}
     * @return the index of the target if found, otherwise -1
     */
    public static <T extends Number & Comparable<T>> int iterativeInterpolationSearch(
            T[] array,
            T target
    ){
        if(array == null || array.length == 0 || target == null){
            return -1;
        }

        int min_index = 0;
        int max_index = array.length - 1;

        double value = target.doubleValue();
        while (min_index <= max_index){
            double lowValue = array[min_index].doubleValue();
            double maxValue = array[max_index].doubleValue();

            if(min_index == max_index){
                return (Double.compare(lowValue, value) == 0) ? min_index : -1;
            }
            if(maxValue == lowValue || value < lowValue || value > maxValue){
                break;
            }

            int pos = min_index +
                    (int)((max_index - min_index) * (value - lowValue) / (maxValue - lowValue));
            double posValue = array[pos].doubleValue();

            if(posValue == value){
                return pos;
            }else if(posValue < value){
                min_index = pos + 1;
            }else{
                max_index = pos - 1;
            }
        }

        return -1;
    }

    // #################
    // Recursive Version
    // #################
    /**
     * Performs a recursive interpolation search on a sorted numeric array.
     *
     * @param array  the sorted array to search
     * @param target the numeric value to find
     * @param <T>    a type extending {@link Number} and {@link Comparable}
     * @return the index of the target if found, otherwise -1
     */
    public static <T extends Number & Comparable<T>> int recursiveInterpolationSearch(
            T[] array,
            T target
    ){
        if(array == null || array.length == 0 || target == null){
            return -1;
        }

        return recursiveInterpolationSearch(array, target, 0, array.length - 1);
    }

    private static <T extends Number & Comparable<T>> int recursiveInterpolationSearch(
            T[] array,
            T target,
            int min_index,
            int max_index
    ){
        if(min_index > max_index){
            return -1;
        }

        double value = target.doubleValue();
        double lowValue = array[min_index].doubleValue();
        double maxValue = array[max_index].doubleValue();
        if(min_index == max_index){
            return (Double.compare(lowValue, value) == 0) ? min_index : -1;
        }
        if(maxValue == lowValue || value < lowValue || value > maxValue){
            return -1;
        }

        int pos = min_index +
                (int)((max_index - min_index) * (value - lowValue) / (maxValue - lowValue));
        double posValue = array[pos].doubleValue();

        if(posValue == value){
            return pos;
        } else if (posValue < value) {
            return recursiveInterpolationSearch(array, target, pos + 1, max_index);
        }else
            return recursiveInterpolationSearch(array, target, min_index, pos - 1);
    }


}

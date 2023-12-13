package cz.robodreams.javadeveloper.homeworks.hw08array;

public class ArraySearch {

    /**
     * B
     * <p>
     * <p>
     * Return index of  element in sorted array (from lowest to highest) using linear search If not found return -1;
     *
     * @param arr
     * @param element
     * @return
     */
    public int linearSearch(int[] arr, int element) {

        if (arr == null) return -1;
        int i = 0;

        while (i < arr.length) {
            if (element == arr[i])
                return i;
            i++;
        }

        return -1;
    }


    /**
     * BONUS TASK
     * <p>
     * <p>
     * Return index of  element in sorted array (from lowest to highest) using binary search If not found return -1;
     *
     * @param arr
     * @param element
     * @return
     */
    public int binarySearch(int[] arr, int element) {

        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return 0;

        int low = 0;
        int high = arr.length - 1;
        int middle;

        while (low <= high) {
            middle = (low + high) / 2;
            if (element < arr[middle]) {
                high = middle - 1;
            } else if (element > arr[middle]) {
                low = middle + 1;
            } else return middle;
        }
        return -1;
    }

}


/**
 * Provide a recursive binary search.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class Searcher
{
    /**
     * Search a sorted array for the index of an item.
     * 
     * @param nums  The data to be searched.
     * @param value The value to be found.
     * @return      The index of value, or -1 if it does not occur.
     */
    public static int search(int[] nums, int value)
    {
        // Search the range nums[0] to nums[nums.length – 1].
        return binarySearch(nums, value, 0, nums.length);
    }

    /**
     * Search nums[first] to nums[last-1] for value.
     * Compare value with the item in the middle of the range.
     * 
     * @param nums The array to be search.
     *             The array must already be sorted in ascending order.
     * @param value The value to search for.
     * @param first The (inclusive) index of the first item to consider.
     * @param last The (exclusive) index of the last item to consider.
     * @return The index of value if found, otherwise -1.
     */
    private static int binarySearch(int[] nums, int value, int first, int last)
    {
        if(first >= last) 
            return -1;
        else {
            int middle = (last + first) / 2;
            if(value == nums[middle]) {
                return middle;
            }
            else if(value < nums[middle]) {
                // Search the left half.
                return binarySearch(nums, value, first, middle);
            }
            else {
                // Search the right half.
                return binarySearch(nums, value, middle + 1, last);
            }
        }   
    }
}

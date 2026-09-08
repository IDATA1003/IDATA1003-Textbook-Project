import java.util.Arrays;
import java.util.Random;

/**
 * Generate some random numbers and demonstrate
 * binary search of sorted data.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class BinarySearchTester
{
    private static Random rand = new Random();
    private int[] nums;

    /**
     * Do a demo/test of a binary search.
     * This constructor creates test data of 1000 integers and then performs 
     * two searches for data: one with a value that does exist in the data,
     * and one with a value that does not exist.
     */
    public BinarySearchTester()
    {
        this(1000);
        
        // Pick a random value known to be in the list and do a search
        int value = nums[rand.nextInt(nums.length)];
        findAndReport(value);
        
        // Now pick a value that does not exist in the list and search again
        value = -100;
        findAndReport(value);        
    }
    
    /**
     * Prepare this object for some experiments with a binary search algorithm:
     * Create a sorted array of random integers of the size given in the 
     * parameter, which we can then use for search experiments.
     * 
     * @param The size of the array to create.
     */
    public BinarySearchTester(int dataSize)
    {
        // Generate the data to be sorted.
        nums = generateRandomData(dataSize);
        Arrays.sort(nums);        
    }
    
    /**
     * Find whether value is in the given array and report
     * either its index or that it is not in the array.
     * 
     * @param nums The data to search.
     * @param value The value to search for.
     */
    public void findAndReport(int value)
    {
        int index = Searcher.search(nums, value);
        if(index >= 0) {
            System.out.println(String.format("%d is in the array at index %d.", value, index));
        }
        else {
            System.out.println(String.format("%d is not in the array.", value));
        }
    }
    
    /**
     * Print out the current test data (the array used for the search).
     */
    public void printData()
    {
        for(int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    /**
     * Generate an array of random integers.
     * 
     * @param size  The size of the array to be created.
     * @return An array of random numbers.
     */
    private static int[] generateRandomData(int size)
    {
        // Values in the range 0 (inclusive) to range (exclusive).
        int range = 10000;
        int[] nums = new int[size];
        for(int i = 0; i < size; i++) {
            nums[i] = rand.nextInt(range);
        }
        return nums;
    }
}

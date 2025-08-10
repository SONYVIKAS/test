import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindMode {

    // Method to find the mode(s) of the array
    public static Set<Integer> findMode(int[] arr) {
        // Return an empty set if the array is empty
        if (arr.length == 0) {
            return new HashSet<>();
        }
        // Return a set with the single element if the array has only one element
        if (arr.length < 2) {
            Set<Integer> singleElementSet = new HashSet<>();
            singleElementSet.add(arr[0]);
            return singleElementSet;
        }

        Map<Integer, Integer> nums = new HashMap<>();
        Integer mode = null;
        Set<Integer> n = new HashSet<>();

        // Count the occurrences of each number in the array
        for (int i : arr) {
            nums.put(i, nums.getOrDefault(i, 0) + 1);
        }

        // Determine the mode(s) based on the highest occurrence count
        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            int num = entry.getKey();
            int val = entry.getValue();
            if (mode == null || val > mode) {
                mode = val;
                n = new HashSet<>();
                n.add(num);
            } else if (val == mode) {
                n.add(num);
            }
        }
        return n;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(findMode(new int[]{3, 5, 6, 2, 6, 7, 8, 3, 6, 6})); // Output: [6]
        System.out.println(findMode(new int[]{1, 2, 3, 4, 5})); // Output: [1, 2, 3, 4, 5]
        System.out.println(findMode(new int[]{2, 1, 2, 1})); // Output: [1, 2]
        System.out.println(findMode(new int[]{1, 2, 3, 2, 4})); // Output: [2]
        System.out.println(findMode(new int[]{})); // Output: []
    }
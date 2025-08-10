
public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(findMode(new int[]{3,5,6,2,6,7,8,3,6,6})); // Expected output: [6]
        System.out.println(findMode(new int[]{1,2,3,4,5})); // Expected output: [1, 2, 3, 4, 5]
        System.out.println(findMode(new int[]{2,1,2,1})); // Expected output: [1, 2]
        System.out.println(findMode(new int[]{1,2,3,2,4})); // Expected output: [2]
        System.out.println(findMode(new int[]{})); // Expected output: []
    }

    public static List<Integer> findMode(int[] arr) {
        // If the array is empty, return an empty list
        if (arr.length == 0) {
            return new ArrayList<>();
        }

        // If the array has only one element, return a list with that element
        if (arr.length < 2) {
            return Arrays.asList(arr[0]);
        }

        // Create a map to store the frequency of each number
        Map<Integer, Integer> nums = new HashMap<>();
        for (int num : arr) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
        }

        // Find the maximum frequency
        int maxFrequency = Collections.max(nums.values());

        // Find the numbers with the maximum frequency
        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        return modes;
    }
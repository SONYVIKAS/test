
public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(findMode(new int[]{3,5,6,2,6,7,8,3,6,6})); // Output: [6]
        System.out.println(findMode(new int[]{1,2,3,4,5})); // Output: [1, 2, 3, 4, 5]
        System.out.println(findMode(new int[]{2,1,2,1})); // Output: [1, 2]
        System.out.println(findMode(new int[]{1,2,3,2,4})); // Output: [2]
        System.out.println(findMode(new int[]{})); // Output: []
    }

    public static Set<Integer> findMode(int[] arr) {
        // If array is empty, return an empty set
        if (arr.length == 0) {
            return new HashSet<>();
        }

        // If array has only one element, return a set with that element
        if (arr.length < 2) {
            return new HashSet<>(Collections.singletonList(arr[0]));
        }

        // Create a map to store the frequency of each number
        Map<Integer, Integer> nums = new HashMap<>();
        for (int num : arr) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
        }

        // Find the mode(s)
        Set<Integer> modes = new HashSet<>();
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                modes.clear();
                modes.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }

        return modes;
    }
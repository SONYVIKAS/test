
// Given an array arr of n unique non-negative integers, how can you most efficiently find a non-negative integer that is not in the array?
// Your solution should return such an integer or null if arr contains all possible integers.
// Analyze the runtime and space complexity of your solution.

public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(findInt(new int[]{0, 2, 1, 3, 4, 5, 11, 32, 42, 50, 100, 6})); // 7
        System.out.println(findInt(new int[]{2, 4, 5, 1, 3})); // 0
        System.out.println(findInt(new int[]{0, 2, 4, 5, 1, 3})); // 6
        System.out.println(findInt(new int[]{0, 2, 4, 5, 1, 3, 6, 8})); // 7
        System.out.println(findInt(new int[]{})); // 0
    }

    public static Integer findInt(int[] arr) {
        // Takes an array and returns a non-negative integer that is not in the original array. Returns null if all integers are in the array.
        // Runtime: O(n)
        // Spacetime: O(n)

        HashMap<Integer, Boolean> arr2 = new HashMap<>();

        // If the length of the array is equal to the maximum allowed integers, there are no missing integers in the array.
        if (arr.length == Integer.MAX_VALUE) {
            return null;
        }

        // Create an O(1) lookup hashtable using the integers from the array as the key, and set the value to True
        for (int i = 0; i < arr.length; i++) {
            arr2.put(arr[i], true);
        }

        // Loop through the length of the array + 1 and check if the number is a key in the hashtable. If it isn't return that number.
        for (int i = 0; i <= arr.length; i++) {
            if (!arr2.containsKey(i)) {
                return i;
            }
        }

        return null;
    }
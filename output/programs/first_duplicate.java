
public class Main {

    // Method to find the first duplicate in an array
    public static int firstDuplicate(int[] a) {
        // Initialize lowest_index to the length of the array
        int lowest_index = a.length;
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            // Check if num is in the rest of the array
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] == num) {
                    // If num is in the rest of the array, update lowest_index
                    if (j < lowest_index) {
                        lowest_index = j;
                    }
                    break;
                }
            }
        }
        // If lowest_index is less than the length of the array, return the element at lowest_index
        if (lowest_index < a.length) {
            return a[lowest_index];
        }
        // If no duplicate is found, return -1
        return -1;
    }

    // Optimized method to find the first duplicate in an array
    public static int firstDuplicateOptimized(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int value = Math.abs(arr[i]) - 1;
            // If the value at index value is positive, make it negative
            if (arr[value] > 0) {
                arr[value] = -arr[value];
            } else {
                // If the value at index value is negative, return value + 1
                return value + 1;
            }
        }
        // If no duplicate is found, return -1
        return -1;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(firstDuplicate(new int[]{8, 4, 6, 2, 6, 4, 7, 9, 5, 8})); // Output: 6
        System.out.println(firstDuplicate(new int[]{2, 3, 3, 1, 5, 2})); // Output: 3
        System.out.println(firstDuplicate(new int[]{1})); // Output: -1
        System.out.println(firstDuplicate(new int[]{2, 1})); // Output: -1
        System.out.println(firstDuplicate(new int[]{2, 2})); // Output: 2
        System.out.println(firstDuplicate(new int[]{2, 4, 3, 5, 1})); // Output: -1

        System.out.println(firstDuplicateOptimized(new int[]{8, 4, 6, 2, 6, 4, 7, 1, 5, 8})); // Output: 6
        System.out.println(firstDuplicateOptimized(new int[]{2, 3, 3, 1, 5, 2})); // Output: 3
        System.out.println(firstDuplicateOptimized(new int[]{1})); // Output: -1
        System.out.println(firstDuplicateOptimized(new int[]{2, 1})); // Output: -1
        System.out.println(firstDuplicateOptimized(new int[]{2, 2})); // Output: 2
        System.out.println(firstDuplicateOptimized(new int[]{2, 4, 3, 5, 1})); // Output: -1
    }
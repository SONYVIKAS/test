
public class Main {

    // Method to count number of items in a list using recursion
    public static int countRecursively(int[] arr, int index) {
        // If index is equal to length of array, return 0
        if (index == arr.length) {
            return 0;
        }

        // Else, return 1 plus recursive call to function with next index
        return 1 + countRecursively(arr, index + 1);
    }

    public static void main(String[] args) {
        // Test cases
        int[] emptyArray = new int[] {};
        int[] array = new int[] {1, 2, 3};

        // Print results of test cases
        System.out.println(countRecursively(emptyArray, 0)); // Expected output: 0
        System.out.println(countRecursively(array, 0)); // Expected output: 3
    }
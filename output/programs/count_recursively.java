
public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(countRecursively(new int[]{})); // Expected output: 0
        System.out.println(countRecursively(new int[]{1, 2, 3})); // Expected output: 3
    }

    // Method to count number of items in an array using recursion
    public static int countRecursively(int[] arr) {
        // If array is empty, return 0
        if (arr.length == 0) {
            return 0;
        }

        // Create a new array excluding the first element
        int[] newArr = Arrays.copyOfRange(arr, 1, arr.length);

        // Return 1 plus the count of the rest of the array
        return 1 + countRecursively(newArr);
    }
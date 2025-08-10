
public class NonNegIntFinder {

    // Method to find a non-negative integer not present in the array
    public static Integer findInt(int[] arr) {
        // Create a HashSet to store the integers from the array for O(1) lookup
        HashSet<Integer> set = new HashSet<>();

        // Add all elements of the array to the set
        for (int num : arr) {
            set.add(num);
        }

        // Iterate from 0 to the length of the array + 1
        // Return the first integer not found in the set
        for (int i = 0; i <= arr.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        // Return null if all integers are present
        return null;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(findInt(new int[]{0, 2, 1, 3, 4, 5, 11, 32, 42, 50, 100, 6})); // 7
        System.out.println(findInt(new int[]{2, 4, 5, 1, 3})); // 0
        System.out.println(findInt(new int[]{0, 2, 4, 5, 1, 3})); // 6
        System.out.println(findInt(new int[]{0, 2, 4, 5, 1, 3, 6, 8})); // 7
        System.out.println(findInt(new int[]{})); // 0
    }
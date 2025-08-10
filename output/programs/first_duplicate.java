
public class FirstDuplicate {

    // Function to find the first duplicate with minimal second occurrence index
    public static int firstDuplicate(int[] a) {
        int lowestIndex = a.length;
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            // Check if the number appears again in the array
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] == num) {
                    if (j < lowestIndex) {
                        lowestIndex = j;
                    }
                    break;
                }
            }
        }
        return (lowestIndex < a.length) ? a[lowestIndex] : -1;
    }

    // Optimized function to find the first duplicate
    public static int firstDuplicateOptimized(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int value = Math.abs(arr[i]) - 1;
            if (arr[value] < 0) {
                return Math.abs(arr[i]);
            }
            arr[value] = -arr[value];
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(firstDuplicateOptimized(new int[]{8, 4, 6, 2, 6, 4, 7, 1, 5, 8})); // 6
        System.out.println(firstDuplicateOptimized(new int[]{2, 3, 3, 1, 5, 2})); // 3
        System.out.println(firstDuplicateOptimized(new int[]{1})); // -1
        System.out.println(firstDuplicateOptimized(new int[]{2, 1})); // -1
        System.out.println(firstDuplicateOptimized(new int[]{2, 2})); // 2
        System.out.println(firstDuplicateOptimized(new int[]{2, 4, 3, 5, 1})); // -1

        // Check if all tests pass
        boolean allTestsPassed = true;
        allTestsPassed &= firstDuplicateOptimized(new int[]{8, 4, 6, 2, 6, 4, 7, 1, 5, 8}) == 6;
        allTestsPassed &= firstDuplicateOptimized(new int[]{2, 3, 3, 1, 5, 2}) == 3;
        allTestsPassed &= firstDuplicateOptimized(new int[]{1}) == -1;
        allTestsPassed &= firstDuplicateOptimized(new int[]{2, 1}) == -1;
        allTestsPassed &= firstDuplicateOptimized(new int[]{2, 2}) == 2;
        allTestsPassed &= firstDuplicateOptimized(new int[]{2, 4, 3, 5, 1}) == -1;

        if (allTestsPassed) {
            System.out.println("All tests passed!");
        }
    }
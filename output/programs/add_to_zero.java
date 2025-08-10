import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(addToZero(new int[]{})); // false
        System.out.println(addToZero(new int[]{1})); // false
        System.out.println(addToZero(new int[]{1, 2, 3})); // false
        System.out.println(addToZero(new int[]{1, 2, 3, -2})); // true
    }

    // Given list of ints, return True if any two nums sum to 0.
    public static boolean addToZero(int[] nums) {
        // Runtime: O(n)
        // Spacetime: O(n)

        if (nums.length < 2) {
            return false;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        for (int num : nums) {
            if (numSet.contains(-num)) {
                return true;
            }
        }

        return false;
    }
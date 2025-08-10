
public class SumList {

    // Using recursion, return the sum of numbers in a list.
    public static int sumList(List<Integer> nums) {
        // Base case: if the list is empty, return 0
        if (nums.isEmpty()) {
            return 0;
        }
        // Recursive case: return the first element plus the sum of the rest
        return nums.get(0) + sumList(nums.subList(1, nums.size()));
    }

    public static void main(String[] args) {
        // Test cases
        assert sumList(List.of(5, 5)) == 10 : "Test case 1 failed";
        assert sumList(List.of(-5, 10, 4)) == 9 : "Test case 2 failed";
        assert sumList(List.of(20)) == 20 : "Test case 3 failed";
        assert sumList(List.of()) == 0 : "Test case 4 failed";

        // If all tests pass, print success message
        System.out.println("ALL TESTS PASSED!");
    }
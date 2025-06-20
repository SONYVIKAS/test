import java.util.Set;
import java.util.HashSet;

public class AddToZero {
    public static boolean addToZero(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        Set<Integer> numSet = new HashSet<Integer>();
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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -2};
        boolean result = addToZero(nums);
        System.out.println(result);
    }
public class AddToZero {
    public static boolean addToZero(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        Set<Integer> numSet = new HashSet<Integer>(nums.length);
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
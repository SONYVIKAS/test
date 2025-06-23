public class Main {
    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {1};
        int[] nums3 = {1, 2, 3};
        int[] nums4 = {1, 2, 3, -2};

        System.out.println(add_to_zero(nums1));
        System.out.println(add_to_zero(nums2));
        System.out.println(add_to_zero(nums3));
        System.out.println(add_to_zero(nums4));
    }

    public static boolean add_to_zero(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        java.util.Set<Integer> numSet = new java.util.HashSet<>();

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
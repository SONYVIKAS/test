public class SumList {
    public static int sumList(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        return nums[0] + sumList(Arrays.copyOfRange(nums, 1, nums.length));
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 5};
        int[] nums2 = {-5, 10, 4};
        int[] nums3 = {20};
        int[] nums4 = {};

        System.out.println(sumList(nums1));
        System.out.println(sumList(nums2));
        System.out.println(sumList(nums3));
        System.out.println(sumList(nums4));
    }
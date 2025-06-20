public class ListSum {
    public static int sumList(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        return nums[0] + sumList(Arrays.copyOfRange(nums, 1, nums.length));
    }

    public static void main(String[] args) {
        int[] nums = {5, 5};
        System.out.println(sumList(nums));

        nums = new int[]{-5, 10, 4};
        System.out.println(sumList(nums));

        nums = new int[]{20};
        System.out.println(sumList(nums));

        nums = new int[]{};
        System.out.println(sumList(nums));
    }
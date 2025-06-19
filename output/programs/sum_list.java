  public class SumList {
      public static int sumList(int[] nums) {
          if (nums.length == 0) {
              return 0;
          }

          return nums[0] + sumList(Arrays.copyOfRange(nums, 1, nums.length));
      }
  }
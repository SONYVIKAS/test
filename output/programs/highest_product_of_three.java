public static int highestProductOfThree(int[] nums) {
    int highest = Integer.MIN_VALUE;
    int lowest = Integer.MAX_VALUE;
    int highestTwo = Integer.MIN_VALUE;
    int lowestTwo = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++) {
        int currentNum = nums[i];
        highestTwo = Math.max(highestTwo, Math.max(highest * currentNum, lowest * currentNum));
        lowestTwo = Math.min(lowestTwo, Math.min(highest * currentNum, lowest * currentNum));
        highest = Math.max(highest, currentNum);
        lowest = Math.min(lowest, currentNum);
    }

    return Math.max(highest * highestTwo, lowest * lowestTwo);
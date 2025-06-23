import org.junit.Test;
import org.junit.Assert;

public class ClimbStairs {
    public static int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2) + climbStairs(n - 3);
    }

    public static int climbStairsDP(int n) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }

        return cache[n];
    }

    public static int climbStairsTab(int n, int[] steps) {
        int[] result = new int[n];
        result[0] = 1;

        for (int i = 0; i < steps.length; i++) {
            for (int j = steps[i]; j < n; j++) {
                int sum = 0;
                for (int k = 0; k <= i; k++) {
                    sum += result[j - steps[k]];
                }
                result[j] = sum;
            }
        }

        return result[n - 1];
    }

    @Test
    public void testClimbStairs() {
        Assert.assertEquals(24, climbStairs(6));
        Assert.assertEquals(81, climbStairs(8));
    }

    @Test
    public void testClimbStairsDP() {
        Assert.assertEquals(24, climbStairsDP(6));
        Assert.assertEquals(81, climbStairsDP(8));
    }

    @Test
    public void testClimbStairsTab() {
        Assert.assertEquals(24, climbStairsTab(6, new int[] { 1, 2, 3 }));
        Assert.assertEquals(14, climbStairsTab(10, new int[] { 2, 3, 5 }));
    }
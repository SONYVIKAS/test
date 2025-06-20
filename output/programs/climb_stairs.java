import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoxClimbingStairs {
    public static int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2) + climbStairs(n - 3);
    }

    public static int climbStairsDynamicProgramming(int n) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;
        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }
        return cache[n];
    }

    public static int climbStairsTabulation(int n, int[] steps) {
        int[] result = new int[n + 1];
        result[0] = 1;
        for (int i = 0; i < steps.length; i++) {
            for (int j = steps[i]; j <= n; j++) {
                int sum = 0;
                for (int k = 0; k <= i; k++) {
                    sum += result[j - steps[k]];
                }
                result[j] = sum;
            }
        }
        return result[n];
    }

    @Test
    public void testClimbStairs() {
        assertEquals(climbStairs(6), 24);
        assertEquals(climbStairs(8), 81);
    }

    @Test
    public void testClimbStairsDynamicProgramming() {
        assertEquals(climbStairsDynamicProgramming(6), 24);
        assertEquals(climbStairsDynamicProgramming(8), 81);
    }

    @Test
    public void testClimbStairsTabulation() {
        assertEquals(climbStairsTabulation(6, new int[] { 1, 2, 3 }), 24);
        assertEquals(climbStairsTabulation(10, new int[] { 2, 3, 5 }), 14);
    }
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClimbStairs {

    // Recursive method to calculate the number of ways to climb stairs
    public static int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2) + climbStairs(n - 3);
    }

    // Dynamic Programming method with memoization to calculate the number of ways to climb stairs
    public static int climbStairsDp(int n) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }
        return cache[n];
    }

    // Dynamic Programming method with tabulation to calculate the number of ways to climb stairs
    public static int climbStairsTab(int n, int[] steps) {
        int[] result = new int[n + 1];
        result[0] = 1;

        for (int i = 0; i < steps.length; i++) {
            for (int j = steps[i]; j <= n; j++) {
                result[j] += result[j - steps[i]];
            }
        }
        return result[n];
    }

    @Test
    public void testClimbStairs() {
        assertEquals(24, climbStairs(6));
        assertEquals(81, climbStairs(8));
    }

    @Test
    public void testClimbStairsDp() {
        assertEquals(24, climbStairsDp(6));
        assertEquals(81, climbStairsDp(8));
    }

    @Test
    public void testClimbStairsTab() {
        assertEquals(24, climbStairsTab(6, new int[]{1, 2, 3}));
        assertEquals(14, climbStairsTab(10, new int[]{2, 3, 5}));
    }
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    // Method to calculate the number of ways to climb stairs
    public static int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n-1) + climbStairs(n-2) + climbStairs(n-3);
    }

    // Method to calculate the number of ways to climb stairs using dynamic programming
    public static int climbStairsDp(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        cache.put(1, 1);
        cache.put(2, 2);

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        cache.put(n, climbStairs(n-1) + climbStairs(n-2) + climbStairs(n-3));
        return cache.get(n);
    }

    // Method to calculate the number of ways to climb stairs using tabulation
    public static int climbStairsTab(int n, int[] steps) {
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

    // Unit tests
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

    public static void main(String[] args) {
        // Run tests
        org.junit.runner.JUnitCore.main("Main");
    }
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestClimbStairs {
    @Test
    public void testClimbStairs() {
        assertEquals(climbStairs(6), 24);
        assertEquals(climbStairs(8), 81);
    }

    @Test
    public void testClimbStairsDP() {
        assertEquals(climbStairsDP(6), 24);
        assertEquals(climbStairsDP(8), 81);
    }

    @Test
    public void testClimbStairsTab() {
        assertEquals(climbStairsTab(6, new int[] {1, 2, 3}), 24);
        assertEquals(climbStairsTab(10, new int[] {2, 3, 5}), 14);
    }
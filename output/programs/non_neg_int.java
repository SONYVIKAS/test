import java.util.HashSet;
import java.util.Set;

public class FindMissingInteger {
    public static int findMissingInteger(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        for (int i = 0; i <= arr.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
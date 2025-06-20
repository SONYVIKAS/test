import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class FindMode {
    public static Set<Integer> findMode(int[] arr) {
        if (arr.length == 0) {
            return new HashSet<Integer>();
        }
        if (arr.length == 1) {
            return new HashSet<Integer>(arr[0]);
        }

        HashMap<Integer, Integer> nums = new HashMap<Integer, Integer>();
        Integer mode = null;
        Set<Integer> n = new HashSet<Integer>();

        for (int i : arr) {
            nums.put(i, nums.getOrDefault(i, 0) + 1);
        }

        for (Integer num : nums.keySet()) {
            Integer val = nums.get(num);
            if (val > mode) {
                mode = val;
                n = new HashSet<Integer>();
                n.add(num);
            } else if (val == mode &&!n.contains(num)) {
                n.add(num);
            }
        }

        return n;
    }
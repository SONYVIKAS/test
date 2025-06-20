import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class ModeFinder {
    public static Set<Integer> findMode(int[] arr) {
        if (arr.length == 0) {
            return new HashSet<Integer>();
        }
        if (arr.length == 1) {
            return new HashSet<Integer>(arr[0]);
        }

        Map<Integer, Integer> nums = new HashMap<Integer, Integer>();
        Integer mode = null;
        Set<Integer> n = new HashSet<Integer>();

        for (int i : arr) {
            nums.put(i, nums.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            if (entry.getValue() > mode) {
                mode = entry.getValue();
                n = new HashSet<Integer>(entry.getKey());
            } else if (entry.getValue() == mode &&!n.contains(entry.getKey())) {
                n.add(entry.getKey());
            }
        }

        return n;
    }
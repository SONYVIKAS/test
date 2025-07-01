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

        for (int i = 0; i < arr.length; i++) {
            if (nums.containsKey(arr[i])) {
                nums.put(arr[i], nums.get(arr[i]) + 1);
            } else {
                nums.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            if (mode == null || entry.getValue() > mode) {
                mode = entry.getValue();
                n = new HashSet<Integer>();
                n.add(entry.getKey());
            } else if (entry.getValue() == mode &&!n.contains(entry.getKey())) {
                n.add(entry.getKey());
            }
        }

        return n;
    }
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SortFractional {
    public static List<String> sortFractional(List<String> nums) {
        List<String> fractionals = new ArrayList<String>();
        for (String num : nums) {
            int index = num.indexOf('.');
            fractionals.add(num.substring(index + 1));
        }
        Collections.sort(fractionals);
        return fractionals;
    }
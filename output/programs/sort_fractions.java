import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SortFractional {
    public static void main(String[] args) {
        Random random = new Random();
        List<Double> nums = new ArrayList<Double>();
        for (int i = 0; i < 100; i++) {
            nums.add(random.nextDouble() * 1000);
        }

        nums.sort((n1, n2) -> {
            String s1 = String.valueOf(n1);
            String s2 = String.valueOf(n2);
            int index1 = s1.indexOf('.');
            int index2 = s2.indexOf('.');
            String frac1 = s1.substring(index1 + 1);
            String frac2 = s2.substring(index2 + 1);
            return frac1.compareTo(frac2);
        });

        System.out.println(nums);
    }
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Double> nums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nums.add(Math.round((random.nextDouble() * 1000) * 1000) / 1000.0);
        }

        nums.sort((num1, num2) -> {
            String num1Str = num1.toString();
            String num2Str = num2.toString();
            int index1 = num1Str.indexOf('.');
            int index2 = num2Str.indexOf('.');
            String fractional1 = num1Str.substring(index1 + 1);
            String fractional2 = num2Str.substring(index2 + 1);
            return fractional1.compareTo(fractional2);
        });

        System.out.println(nums);
    }
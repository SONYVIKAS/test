
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // Generate random number between 0 and 1000 with 3 decimal places
            nums.add(String.format("%.3f", rand.nextDouble() * 1000));
        }

        // Print sorted fractional parts
        System.out.println(sortFractional(nums));
    }

    // Sort nums using only the fractional portion of each number.
    // Example: 30.12 is bigger than 100.01
    public static List<String> sortFractional(List<String> nums) {
        for (int i = 0; i < nums.size(); i++) {
            String num = nums.get(i);
            int index = num.indexOf('.');
            // Get the fractional part of the number
            nums.set(i, num.substring(index + 1));
        }
        Collections.sort(nums);
        return nums;
    }
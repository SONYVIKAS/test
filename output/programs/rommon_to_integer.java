import java.util.Map;

public class RomanToInteger {
    public static int romanToInt(String s) {
        // Create a map to store Roman numerals and their integer values
        Map<Character, Integer> romanDict = new HashMap<>();
        romanDict.put('I', 1);
        romanDict.put('V', 5);
        romanDict.put('X', 10);
        romanDict.put('L', 50);
        romanDict.put('C', 100);
        romanDict.put('D', 500);
        romanDict.put('M', 1000);

        int result = 0;
        // Iterate over the string characters
        for (int i = 0; i < s.length(); i++) {
            // Check if the current numeral is less than the next numeral
            if (i < s.length() - 1 && romanDict.get(s.charAt(i)) < romanDict.get(s.charAt(i + 1))) {
                result -= romanDict.get(s.charAt(i)); // Subtract if smaller
            } else {
                result += romanDict.get(s.charAt(i)); // Add otherwise
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(romanToInt("III"));    // Output: 3
        System.out.println(romanToInt("IV"));     // Output: 4
        System.out.println(romanToInt("IX"));     // Output: 9
        System.out.println(romanToInt("LVIII"));  // Output: 58
        System.out.println(romanToInt("MCMXCIV"));// Output: 1994
    }
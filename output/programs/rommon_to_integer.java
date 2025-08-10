
public class Main {
    public static void main(String[] args) {
        System.out.println(romanToInt("III")); // 3
        System.out.println(romanToInt("IV")); // 4
        System.out.println(romanToInt("IX")); // 9
        System.out.println(romanToInt("LVIII")); // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }

    // Function to convert Roman numerals to Integer
    public static int romanToInt(String s) {
        // Create a dictionary to map Roman numerals to their integer values
        HashMap<Character, Integer> romanDict = new HashMap<>();
        romanDict.put('I', 1);
        romanDict.put('V', 5);
        romanDict.put('X', 10);
        romanDict.put('L', 50);
        romanDict.put('C', 100);
        romanDict.put('D', 500);
        romanDict.put('M', 1000);

        int result = 0;

        // Iterate over the string
        for (int i = 0; i < s.length(); i++) {
            // If the current Roman numeral is less than the next one, subtract its value
            if (i < s.length() - 1 && romanDict.get(s.charAt(i)) < romanDict.get(s.charAt(i + 1))) {
                result -= romanDict.get(s.charAt(i));
            } else {
                // Otherwise, add its value
                result += romanDict.get(s.charAt(i));
            }
        }

        return result;
    }
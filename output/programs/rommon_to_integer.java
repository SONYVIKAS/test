public class RomanNumerals {
    private static final Map<Character, Integer> ROMAN_TO_INT = Map.of(
        'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000
    );

    public static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && ROMAN_TO_INT.get(s.charAt(i)) < ROMAN_TO_INT.get(s.charAt(i + 1))) {
                result -= ROMAN_TO_INT.get(s.charAt(i));
            } else {
                result += ROMAN_TO_INT.get(s.charAt(i));
            }
        }
        return result;
    }
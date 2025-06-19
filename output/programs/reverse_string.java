public class ReverseString {
    public static String reverseString(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char[] chars = string.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String input = "hello";
        String reversedString = reverseString(input);
        System.out.println("Reversed string: " + reversedString);
    }
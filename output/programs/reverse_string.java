public class ReverseString {
    public static void main(String[] args) {
        String string = "hello";
        System.out.println(revStr(string));
        System.out.println(revStr2(string));
        System.out.println(revStr3(string));
    }

    public static String revStr(String string) {
        String reversedStr = "";
        char[] strArray = string.toCharArray();
        for (int i = strArray.length - 1; i >= 0; i--) {
            reversedStr += strArray[i];
        }
        return reversedStr;
    }

    public static String revStr2(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public static String revStr3(String string) {
        if (string.length() == 0) {
            return string;
        }
        return string.charAt(string.length() - 1) + revStr3(string.substring(0, string.length() - 1));
    }
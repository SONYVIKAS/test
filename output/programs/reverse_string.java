public static String revStr(String string) {
    String reversedStr = "";
    char[] strArray = string.toCharArray();
    for (int i = strArray.length - 1; i >= 0; i--) {
        reversedStr += strArray[i];
    }
    return reversedStr;
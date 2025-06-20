public int romanToInt(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
        switch (s.charAt(i)) {
            case 'I':
                result += 1;
                break;
            case 'V':
                result += 5;
                break;
            case 'X':
                result += 10;
                break;
            case 'L':
                result += 50;
                break;
            case 'C':
                result += 100;
                break;
            case 'D':
                result += 500;
                break;
            case 'M':
                result += 1000;
                break;
        }
        if (i < s.length() - 1 && getValue(s.charAt(i)) < getValue(s.charAt(i + 1))) {
            result -= 2 * getValue(s.charAt(i));
        }
    }
    return result;
}

private int getValue(char c) {
    switch (c) {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            return 0;
    }
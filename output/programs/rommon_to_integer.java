def roman_to_int(s: str) -> int:
    roman_dict = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    result = 0
    for i in range(len(s)):
        if i < len(s) - 1 and roman_dict[s[i]] < roman_dict[s[i+1]]:
            result -= roman_dict[s[i]]
        else:
            result += roman_dict[s[i]]
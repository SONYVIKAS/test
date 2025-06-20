import java.util.List;
import java.util.ArrayList;

public class ValidParenthesisPermutations {
    public static List<String> validParenthesisPermutations(int num) {
        List<String> result = new ArrayList<>();

        recurse("", num, num, result);

        return result;
    }

    private static void recurse(String substr, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(substr);
            return;
        } else if (left == 0) {
            recurse(substr + ')', left, right - 1, result);
        } else if (left < right) {
            recurse(substr + '(', left - 1, right, result);
            recurse(substr + ')', left, right - 1, result);
        } else if (left == right) {
            recurse(substr + '(', left - 1, right, result);
        }
    }
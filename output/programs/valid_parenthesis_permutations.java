import java.util.List;

public class Main {

    // Given a num, get all the valid parenthesis permutations.
    public static List<String> validParensPerms(int num) {
        List<String> result = new ArrayList<>();

        recurse(result, "", num, num);

        return result;
    }

    private static void recurse(List<String> result, String substr, int left, int right) {
        // Base case: if num of open used == num of closed used
        if (left == 0 && right == 0) {
            result.add(substr);
            return;
        }

        // Num of opens used up, then next to the end must be closed
        else if (left == 0) {
            recurse(result, substr + ")", left, right - 1);
        }

        // If there are less opens used than closes, we can add either open or close
        else if (left < right) {
            recurse(result, substr + "(", left - 1, right);
            recurse(result, substr + ")", left, right - 1);
        }

        // If there are equal number of opens and closes used, we can only add open
        else if (left == right) {
            recurse(result, substr + "(", left - 1, right);
        }
    }

    public static void main(String[] args) {
        System.out.println(validParensPerms(1)); // ['()']
        System.out.println(validParensPerms(2)); // ['(())', '()()']
        System.out.println(validParensPerms(3)); // ['((()))', '(()())', '(())()', '()(())', '()()()']
        System.out.println(validParensPerms(4)); // ['(((())))', '((()()))', '((())())', '((()))()', '(()(()))', '(()()())', '(()())()', '(())(())', '(())()()', '()((()))', '()(()())', '()(())()', '()()(())', '()()()()']
    }
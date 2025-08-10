import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ValidParenthesisPermutations {

    // Function to generate all valid parenthesis permutations
    public List<String> validParensPerms(int num) {
        List<String> result = new ArrayList<>();
        recurse("", num, num, result);
        return result;
    }

    // Helper recursive function to build permutations
    private void recurse(String substr, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(substr);
            return;
        } else if (left == 0) {
            recurse(substr + ")", left, right - 1, result);
        } else if (left < right) {
            recurse(substr + "(", left - 1, right, result);
            recurse(substr + ")", left, right - 1, result);
        } else if (left == right) {
            recurse(substr + "(", left - 1, right, result);
        }
    }

    // Unit tests for the validParensPerms function
    public static class Testing {
        @Test
        public void testValidParensPerms() {
            ValidParenthesisPermutations vpp = new ValidParenthesisPermutations();
            assertEquals(List.of("()"), vpp.validParensPerms(1));
            assertEquals(List.of("(())", "()()"), vpp.validParensPerms(2));
            assertEquals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), vpp.validParensPerms(3));
            assertEquals(List.of("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"), vpp.validParensPerms(4));
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("ValidParenthesisPermutations$Testing");
    }
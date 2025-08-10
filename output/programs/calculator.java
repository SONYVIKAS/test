import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Stack;

public class Calculator {

    // Method to evaluate an expression without using eval
    public static double evaluate(String expression) {
        expression = expression.replaceAll(" ", "");
        Stack<Integer> parensIndices = new Stack<>();
        StringBuilder storage = new StringBuilder();
        int[] operators = new int[expression.length()];

        for (int i = 0; i < expression.length(); i++) {
            char charAt = expression.charAt(i);
            if (charAt == '(') {
                parensIndices.push(i);
            } else if (charAt == ')') {
                int start = parensIndices.pop() + 1;
                double total = calculate(expression.substring(start, i), operators);
                storage.setLength(start - 1);
                storage.append(total);
            } else {
                storage.append(charAt);
            }

            if ("*/+-".indexOf(charAt) != -1) {
                operators[i] = 1;
            }
        }

        return calculate(storage.toString(), operators);
    }

    // Method to calculate the result of an expression
    private static double calculate(String innerExpression, int[] operators) {
        double total = 0;
        StringBuilder newExpr = new StringBuilder(innerExpression);

        for (int i = 0; i < innerExpression.length(); i++) {
            char charAt = innerExpression.charAt(i);
            if ("*/+-".indexOf(charAt) != -1) {
                operators[i] = 1;
            }
        }

        for (int i = 0; i < newExpr.length(); i++) {
            if (operators[i] == 1) {
                char operator = newExpr.charAt(i);
                double prev = Double.parseDouble(String.valueOf(newExpr.charAt(i - 1)));
                double next = Double.parseDouble(String.valueOf(newExpr.charAt(i + 1)));
                switch (operator) {
                    case '*':
                        total = prev * next;
                        break;
                    case '/':
                        total = prev / next;
                        break;
                    case '-':
                        total = prev - next;
                        break;
                    case '+':
                        total = prev + next;
                        break;
                }
                newExpr.replace(i - 1, i + 2, String.valueOf(total));
                i = -1; // Reset loop to re-evaluate the expression
            }
        }

        return total;
    }

    // Unit tests for the evaluate method
    public static class Testing {

        @Test
        public void testNoParens() {
            assertEquals(7, evaluate("1 + 3 * 2"));
        }

        @Test
        public void testSingleParens() {
            assertEquals(16, evaluate("(1 + 7) * 2"));
        }

        @Test
        public void testOperationsOrder() {
            assertEquals(29, evaluate("4 * (1 + 7) - 3"));
        }

        @Test
        public void testLongParens() {
            assertEquals(-6, evaluate("(4 - 7 - 1) * 3"));
        }

        @Test
        public void testDecimals() {
            assertEquals(6.25, evaluate("(1.2 + 1.3) * 2.5"));
        }
    }
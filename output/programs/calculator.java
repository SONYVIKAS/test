
public class Main {

    public static void main(String[] args) {
        System.out.println(evaluate("1 + 3 * 2")); // 7
        System.out.println(evaluate("(1 + 7) * 2")); // 16
        System.out.println(evaluate("4 * (1 + 7) - 3")); // 29
        System.out.println(evaluate("(4 - 7 - 1) * 3")); // -6
        System.out.println(evaluate("(1.2 + 1.3) * 2.5")); // 6.25
    }

    public static double evaluate(String expression) {
        expression = expression.replace(" ", "");
        List<String> storage = new ArrayList<>();
        Stack<Integer> parensIndices = new Stack<>();
        Map<Character, List<Integer>> operators = new HashMap<>();
        operators.put('*', new ArrayList<>());
        operators.put('/', new ArrayList<>());
        operators.put('+', new ArrayList<>());
        operators.put('-', new ArrayList<>());

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                parensIndices.push(i);
            } else if (ch == ')') {
                int start = parensIndices.pop() + 1;
                double total = calculate(expression.substring(start, i), operators);
                storage = storage.subList(0, start - 1);
                storage.add(String.valueOf(total));
            } else {
                storage.add(String.valueOf(ch));
            }

            if (operators.containsKey(ch)) {
                operators.get(ch).add(i);
            }
        }

        String stringExpression = String.join("", storage);
        return calculate(stringExpression, operators);
    }

    public static double calculate(String innerExpression, Map<Character, List<Integer>> operators) {
        double total = 0;
        operators = new HashMap<>();
        operators.put('*', new ArrayList<>());
        operators.put('/', new ArrayList<>());
        operators.put('+', new ArrayList<>());
        operators.put('-', new ArrayList<>());
        String newExpr = innerExpression;

        for (int i = 0; i < innerExpression.length(); i++) {
            char ch = innerExpression.charAt(i);
            if (operators.containsKey(ch)) {
                operators.get(ch).add(i);
            }
        }

        while (!operators.get('*').isEmpty()) {
            int opIndex = operators.get('*').remove(operators.get('*').size() - 1);
            double prev = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex - 1)));
            double next = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex + 1)));
            total = prev * next;
            newExpr = newExpr.substring(0, opIndex - 1) + total + newExpr.substring(opIndex + 2);
        }

        while (!operators.get('/').isEmpty()) {
            int opIndex = operators.get('/').remove(operators.get('/').size() - 1);
            double prev = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex - 1)));
            double next = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex + 1)));
            total = prev / next;
            newExpr = newExpr.substring(0, opIndex - 1) + total + newExpr.substring(opIndex + 2);
        }

        while (!operators.get('-').isEmpty()) {
            int opIndex = operators.get('-').remove(operators.get('-').size() - 1);
            double prev = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex - 1)));
            double next = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex + 1)));
            total = prev - next;
            newExpr = newExpr.substring(0, opIndex - 1) + total + newExpr.substring(opIndex + 2);
        }

        while (!operators.get('+').isEmpty()) {
            int opIndex = operators.get('+').remove(operators.get('+').size() - 1);
            double prev = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex - 1)));
            double next = Double.parseDouble(String.valueOf(newExpr.charAt(opIndex + 1)));
            total = prev + next;
            newExpr = newExpr.substring(0, opIndex - 1) + total + newExpr.substring(opIndex + 2);
        }

        return total;
    }
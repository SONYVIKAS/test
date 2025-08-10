
// Class to check if a string has balanced brackets
public class BalancedBrackets {

    // Method to check if a string has balanced brackets
    public static boolean hasBalancedBrackets(String phrase) {
        // Define the bracket pairs
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(']', '[');
        brackets.put('}', '{');
        brackets.put('>', '<');
        brackets.put(')', '(');

        // Define the sets of open and close brackets
        Set<Character> openBrackets = new HashSet<>(Arrays.asList('[', '{', '<', '('));
        Set<Character> closeBrackets = new HashSet<>(Arrays.asList(']', '}', '>', ')'));

        // Stack to keep track of the seen open brackets
        Stack<Character> seen = new Stack<>();

        // Iterate over the characters in the phrase
        for (char let : phrase.toCharArray()) {
            // If the character is an open bracket, push it to the stack
            if (openBrackets.contains(let)) {
                seen.push(let);
            }
            // If the character is a close bracket, check if it matches the last seen open bracket
            if (closeBrackets.contains(let)) {
                if (!seen.empty() && brackets.get(let) == seen.peek()) {
                    seen.pop();
                } else {
                    return false;
                }
            }
        }

        // If the stack is empty, the brackets are balanced
        return seen.empty();
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println(hasBalancedBrackets("<ok>")); // true
        System.out.println(hasBalancedBrackets("<[ok]>")); // true
        System.out.println(hasBalancedBrackets("<[{(yay)}]>")); // true
        System.out.println(hasBalancedBrackets("No brackets here!")); // true

        System.out.println(hasBalancedBrackets("(Oops!){")); // false
        System.out.println(hasBalancedBrackets("{[[This has too many open square brackets.]}")); // false
        System.out.println(hasBalancedBrackets(">")); // false
        System.out.println(hasBalancedBrackets("(This has {too many} ) closers. )")); // false
        System.out.println(hasBalancedBrackets("<{Not Ok>}")); // false
    }
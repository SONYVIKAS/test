  import java.util.Stack;

  public class BracketBalance {
      public static boolean hasBalancedBrackets(String phrase) {
          Stack<Character> stack = new Stack<>();
          for (char c : phrase.toCharArray()) {
              if (c == '[' || c == '{' || c == '(' || c == '<') {
                  stack.push(c);
              } else if (c == ']' || c == '}' || c == ')' || c == '>') {
                  if (!stack.isEmpty() && isMatchingBracket(stack.peek(), c)) {
                      stack.pop();
                  } else {
                      return false;
                  }
              }
          }
          return stack.isEmpty();
      }

      private static boolean isMatchingBracket(char open, char close) {
          return (open == '[' && close == ']') || (open == '{' && close == '}') ||
                 (open == '(' && close == ')') || (open == '<' && close == '>');
      }
  }
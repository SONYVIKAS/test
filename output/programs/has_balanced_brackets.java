  import java.util.Set;
  import java.util.Stack;
  import java.util.HashMap;
  import org.junit.Test;
  import static org.junit.Assert.assertTrue;
  import static org.junit.Assert.assertFalse;

  public class BalancedBrackets {
      public static boolean hasBalancedBrackets(String phrase) {
          HashMap<Character, Character> brackets = new HashMap<Character, Character>();
          brackets.put(']', '[');
          brackets.put('}', '{');
          brackets.put('[', '[');
          brackets.put('>', '<');
          brackets.put(')', '(');
          Set<Character> openBrackets = Set.of('[', '{', '[', '<', '(');
          Set<Character> closeBrackets = Set.of(']', '}', ']', '>', ')');
          Stack<Character> seen = new Stack<Character>();

          for (char let : phrase.toCharArray()) {
              if (openBrackets.contains(let)) {
                  seen.push(let);
              }
              if (closeBrackets.contains(let)) {
                  if (!seen.isEmpty() && brackets.get(let) == seen.peek()) {
                      seen.pop();
                  } else {
                      return false;
                  }
              }
          }

          return seen.isEmpty();
      }
  }
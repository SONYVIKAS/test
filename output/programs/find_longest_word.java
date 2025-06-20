  import org.junit.Test;
  import static org.junit.Assert.assertEquals;

  public class FindLongestWord {
      public static int findLongestWord(String[] words) {
          int longest = 0;

          for (String word : words) {
              longest = Math.max(word.length(), longest);
          }

          return longest;
      }

      @Test
      public void testFindLongestWord() {
          assertEquals(findLongestWord(new String[] {"hi", "hello"}), 5);
          assertEquals(findLongestWord(new String[] {"Balloonicorn", "Hackbright"}), 12);
      }
  }
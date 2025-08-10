import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeRanges {

    public static List<int[]> mergeRanges(List<int[]> lst) {
        // Sort the meeting times by start time
        Collections.sort(lst, Comparator.comparingInt(a -> a[0]));

        // Initialize the merged range list with the first meeting time
        List<int[]> mergedRange = new ArrayList<>();
        mergedRange.add(lst.get(0));

        // Iterate through the sorted meeting times
        for (int i = 1; i < lst.size(); i++) {
            int[] current = lst.get(i);
            int[] lastMerged = mergedRange.get(mergedRange.size() - 1);

            // If the current meeting overlaps with the last merged meeting, merge them
            if (lastMerged[1] >= current[0]) {
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // Otherwise, add the current meeting as a new entry in the merged list
                mergedRange.add(current);
            }
        }

        return mergedRange;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[]> test1 = Arrays.asList(new int[]{3, 5}, new int[]{4, 8}, new int[]{10, 12}, new int[]{9, 10}, new int[]{0, 1});
        List<int[]> test2 = Arrays.asList(new int[]{0, 3}, new int[]{3, 5}, new int[]{4, 8}, new int[]{10, 12}, new int[]{9, 10});
        List<int[]> test3 = Arrays.asList(new int[]{0, 3}, new int[]{3, 5});
        List<int[]> test4 = Arrays.asList(new int[]{0, 3}, new int[]{3, 5}, new int[]{7, 8});
        List<int[]> test5 = Arrays.asList(new int[]{1, 5}, new int[]{2, 3});

        // Print results
        System.out.println(mergeRanges(test1));
        System.out.println(mergeRanges(test2));
        System.out.println(mergeRanges(test3));
        System.out.println(mergeRanges(test4));
        System.out.println(mergeRanges(test5));
    }
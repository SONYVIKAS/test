import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MergeRanges {
    public static List<int[]> mergeRanges(List<int[]> meetingTimes) {
        Collections.sort(meetingTimes, (a, b) -> a[0] - b[0]);

        List<int[]> mergedRanges = new ArrayList<>();
        mergedRanges.add(meetingTimes.get(0));

        for (int[] meetingTime : meetingTimes.subList(1, meetingTimes.size())) {
            int[] lastRange = mergedRanges.get(mergedRanges.size() - 1);
            if (lastRange[1] >= meetingTime[0]) {
                lastRange[1] = Math.max(lastRange[1], meetingTime[1]);
            } else {
                mergedRanges.add(meetingTime);
            }
        }

        return mergedRanges;
    }
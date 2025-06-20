import java.util.ArrayList;
import java.util.List;

public class MergeRanges {
    public static void main(String[] args) {
        List<Range> meetingTimes = new ArrayList<>();
        meetingTimes.add(new Range(3, 5));
        meetingTimes.add(new Range(4, 8));
        meetingTimes.add(new Range(10, 12));
        meetingTimes.add(new Range(9, 10));
        meetingTimes.add(new Range(0, 1));

        List<Range> mergedRanges = mergeRanges(meetingTimes);
        for (Range range : mergedRanges) {
            System.out.println("(" + range.start + ", " + range.end + ")");
        }
    }

    public static List<Range> mergeRanges(List<Range> meetingTimes) {
        meetingTimes.sort((r1, r2) -> r1.start - r2.start);

        List<Range> mergedRanges = new ArrayList<>();
        mergedRanges.add(meetingTimes.get(0));

        for (Range currentRange : meetingTimes.subList(1, meetingTimes.size())) {
            Range lastRange = mergedRanges.get(mergedRanges.size() - 1);

            if (lastRange.end >= currentRange.start) {
                lastRange.end = Math.max(lastRange.end, currentRange.end);
            } else {
                mergedRanges.add(currentRange);
            }
        }

        return mergedRanges;
    }
}

class Range {
    int start;
    int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
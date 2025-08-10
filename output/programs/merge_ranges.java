
class Meeting {
    int start;
    int end;

    Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) {
        // Test cases
        ArrayList<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(3, 5));
        meetings.add(new Meeting(4, 8));
        meetings.add(new Meeting(10, 12));
        meetings.add(new Meeting(9, 10));
        meetings.add(new Meeting(0, 1));
        System.out.println(mergeRanges(meetings));
    }

    public static ArrayList<Meeting> mergeRanges(ArrayList<Meeting> meetings) {
        // Sort the meetings by start time
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                return m1.start - m2.start;
            }
        });

        ArrayList<Meeting> mergedMeetings = new ArrayList<>();
        mergedMeetings.add(meetings.get(0));

        // Merge meetings
        for (int i = 1; i < meetings.size(); i++) {
            Meeting lastMeeting = mergedMeetings.get(mergedMeetings.size() - 1);
            Meeting currentMeeting = meetings.get(i);

            if (lastMeeting.end >= currentMeeting.start) {
                lastMeeting.end = Math.max(lastMeeting.end, currentMeeting.end);
            } else {
                mergedMeetings.add(currentMeeting);
            }
        }

        return mergedMeetings;
    }
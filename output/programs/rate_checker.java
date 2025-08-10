import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;

public class RateChecker {

    private int actions;
    private int seconds;
    private Queue<LocalTime> times; // Queue to keep track of check times

    public RateChecker(int actions, int seconds) {
        this.actions = actions;
        this.seconds = seconds;
        this.times = new LinkedList<>();
    }

    public boolean check() {
        LocalTime currentTime = LocalTime.now();
        times.add(currentTime); // Add current check time to the queue

        if (times.size() > actions) {
            return false; // Quick return if queue size exceeds actions
        }

        LocalTime oldestTime = times.poll(); // Remove the oldest time from the queue
        Duration duration = Duration.between(oldestTime, currentTime);
        return duration.getSeconds() <= seconds; // Check if the duration is within the allowed seconds
    }

    public static void main(String[] args) {
        RateChecker clicks = new RateChecker(3, 14);
        System.out.println(clicks.check()); // Example usage
        System.out.println(clicks.check());
        System.out.println(clicks.check());
        System.out.println(clicks.check());
    }
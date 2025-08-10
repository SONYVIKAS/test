import java.time.LocalTime;
import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;

public class RateChecker {

    private int actions;
    private int seconds;
    private Queue<LocalTime> times;  // Queue needed to keep track of how many times it's being checked

    // Constructor
    public RateChecker(int actions, int seconds) {
        this.actions = actions;
        this.seconds = seconds;
        this.times = new LinkedList<>();  // Initialize the queue
    }

    public boolean check() {
        LocalTime currentTime = LocalTime.now();
        times.add(currentTime);  // Append each check time to the queue

        // First check if the queue is greater than the number of actions
        if (times.size() > actions) {
            times.poll();  // Remove the oldest time
            return false;  // Returning false here gives a quick win
        }

        // Check times in the queue
        // (Slower approach if the queue is really huge, hence why queue length vs. actions should be checked first.)
        Duration duration = Duration.between(times.peek(), currentTime);
        return duration.getSeconds() <= seconds;
    }

    public static void main(String[] args) {
        RateChecker clicks = new RateChecker(3, 14);
        System.out.println(clicks.check());
        System.out.println(clicks.check());
        System.out.println(clicks.check());
        System.out.println(clicks.check());
    }
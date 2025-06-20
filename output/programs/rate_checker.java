import java.util.Queue;
import java.util.LinkedList;
import java.time.LocalTime;
import java.time.Duration;

public class RateChecker {
    private final int actions;
    private final int seconds;
    private Queue<LocalTime> times;

    public RateChecker(int actions, int seconds) {
        this.actions = actions;
        this.seconds = seconds;
        this.times = new LinkedList<>();
    }

    public boolean check() {
        LocalTime currentTime = LocalTime.now();
        times.add(currentTime);

        if (times.size() > actions) {
            return false;
        }

        LocalTime firstTime = times.peek();
        Duration duration = Duration.between(firstTime, currentTime);

        if (duration.getSeconds() > seconds) {
            times.remove();
        }

        return times.size() >= actions;
    }
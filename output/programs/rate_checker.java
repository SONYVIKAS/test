import java.time.LocalTime;
import java.time.Duration;
import java.util.LinkedList;

public class RateChecker {
    private int actions;
    private int seconds;
    private LinkedList<LocalTime> times;

    public RateChecker(int actions, int seconds) {
        this.actions = actions;
        this.seconds = seconds;
        this.times = new LinkedList<LocalTime>();
    }

    public boolean check() {
        LocalTime current_time = LocalTime.now();
        times.add(current_time);

        if (times.size() > actions) {
            return false;
        }

        return Duration.between(times.getLast(), current_time).getSeconds() <= seconds;
    }
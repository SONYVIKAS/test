public class Ship {
    private boolean docked;

    public boolean isDocked() {
        return docked;
    }

    public void dock() {
        docked = true;
    }

    public void undock() {
        docked = false;
    }
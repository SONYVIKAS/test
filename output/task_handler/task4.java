public class ReFuelingShip {
    public static void main(String[] args) {
        double initialLocation = 100.0;
        double destination = 200.0;
        double distance = destination - initialLocation;
        double fuelEfficiency = 10.0;
        double fuelRequired = distance / fuelEfficiency;
        double fuelInTank = 50.0;
        if (fuelRequired <= fuelInTank) {
            System.out.println("The ship can reach the destination.");
        } else {
            System.out.println("The ship needs to refuel.");
        }
    }
public class RefillShip {
    public static void main(String[] args) {
        int fuelLevel = 50;
        int refuelAmount = 20;
        int maxFuel = 100;

        if (fuelLevel + refuelAmount > maxFuel) {
            System.out.println("Cannot refuel, maximum fuel capacity reached.");
        } else {
            fuelLevel += refuelAmount;
            System.out.println("Refueling complete, current fuel level: " + fuelLevel);
        }
    }
public class RefuelingShip {
    public static void main(String[] args) {
        int currentFuel = 10;
        int distanceToNextDestination = 20;
        int fuelCapacity = 50;
        int fuelConsumptionPerUnitDistance = 2;

        while (currentFuel < distanceToNextDestination) {
            int fuelToRefuel = fuelCapacity - currentFuel;
            currentFuel += fuelToRefuel;
            distanceToNextDestination -= fuelToRefuel / fuelConsumptionPerUnitDistance;
        }

        System.out.println("Ship has reached its next destination with " + currentFuel + " units of fuel remaining.");
    }
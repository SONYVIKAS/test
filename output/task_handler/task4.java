public class ReFuelShip {
    public static void main(String[] args) {
        int currentFuel = 10;
        int fuelCapacity = 20;
        int distanceToTravel = 50;

        while (currentFuel < distanceToTravel) {
            currentFuel += fuelCapacity;
            System.out.println("Refueling ship with " + fuelCapacity + " units of fuel");
        }

        System.out.println("Ship is ready to travel");
    }
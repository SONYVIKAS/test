public class RefuelingShip {
    public static void main(String[] args) {
        int currentFuel = 10;
        int distanceToTravel = 200;
        int fuelCapacity = 50;
        int fuelConsumptionPerMile = 2;
        int refuelingStationDistance = 100;
        int refuelingStationCapacity = 20;

        while (currentFuel < distanceToTravel) {
            if (currentFuel + refuelingStationCapacity >= distanceToTravel) {
                currentFuel += refuelingStationCapacity;
            } else {
                currentFuel = fuelCapacity;
            }

            currentFuel -= fuelConsumptionPerMile * (refuelingStationDistance - distanceToTravel);
            distanceToTravel = refuelingStationDistance;
        }

        System.out.println("Ship has reached its destination with " + currentFuel + " fuel left.");
    }
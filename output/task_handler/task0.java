public class ShipDocking {
    private String shipName;

    public ShipDocking(String shipName) {
        this.shipName = shipName;
    }

    public Ship dockShip(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException("Ship cannot be null");
        }

        if (!ship.name.equals(shipName)) {
            throw new IllegalArgumentException("Ship name must match docking station name");
        }

        if (!ship.docked) {
            ship.docked = true;
        }

        return ship;
    }
}

public class Ship {
    public String name;
    public boolean docked;

    public Ship(String name, boolean docked) {
        this.name = name;
        this.docked = docked;
    }
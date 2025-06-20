public class Ship {
    private String name;
    private int size;
    private String location;

    public Ship(String name, int size, String location) {
        this.name = name;
        this.size = size;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getLocation() {
        return location;
    }
}

public class Docking {
    private String name;
    private int size;
    private String location;

    public Docking(String name, int size, String location) {
        this.name = name;
        this.size = size;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getLocation() {
        return location;
    }
}

public class ShipDocking {
    private Ship ship;
    private Docking docking;
    private String status;

    public ShipDocking(Ship ship, Docking docking, String status) {
        this.ship = ship;
        this.docking = docking;
        this.status = status;
    }

    public Ship getShip() {
        return ship;
    }

    public Docking getDocking() {
        return docking;
    }

    public String getStatus() {
        return status;
    }

    public void dockShip(Ship ship, Docking docking) {
        this.ship = ship;
        this.docking = docking;
        this.status = "Docked";
    }
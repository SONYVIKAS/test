class ShipDocking {
    private int x;
    private int y;

    public ShipDocking(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(String direction) {
        switch (direction) {
            case "north":
                y++;
                break;
            case "south":
                y--;
                break;
            case "east":
                x++;
                break;
            case "west":
                x--;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
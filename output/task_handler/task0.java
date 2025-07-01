public class ShipDocking {
    public static void main(String[] args) {
        int shipX = 10;
        int shipY = 10;
        int bayX = 20;
        int bayY = 20;
        int distance = (int) Math.sqrt(Math.pow(shipX - bayX, 2) + Math.pow(shipY - bayY, 2));
        if (distance < 10) {
            System.out.println("Ship is docked");
        } else {
            System.out.println("Ship is not docked");
        }
    }
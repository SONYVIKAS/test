public static void reFuelShip(int[] ship) {
    int fuel = 0;
    int i = 0;
    while (i < ship.length) {
        if (ship[i] == fuel) {
            fuel++;
            ship[i]--;
        } else {
            i++;
        }
    }
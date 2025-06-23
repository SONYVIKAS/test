public class CustomsCheck {
    public static boolean checkItem(double weight, String originCountry) {
        if (weight > 10 && originCountry == "China") {
            return false;
        } else if (weight > 20 && originCountry == "USA") {
            return false;
        } else {
            return true;
        }
    }
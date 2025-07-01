double weight = 25.0;
String unit = "lb";

if (unit.equals("lb")) {
    weight *= 0.453592;
}

if (weight > 30.0) {
    System.out.println("Package is too heavy.");
} else {
    System.out.println("Package is allowed.");
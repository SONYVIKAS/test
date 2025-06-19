double customsDuty(String country, double price) {
    switch (country) {
        case "USA":
            return price * 0.05;
        case "UK":
            return price * 0.10;
        case "China":
            return price * 0.02;
        default:
            return 0;
    }
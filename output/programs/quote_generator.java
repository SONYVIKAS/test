
class QuoteGenerator {
    private int lastRandNum = -1; // To store the last generated random number
    private String[] quotes; // Array to store quotes

    // Constructor to initialize the quotes array
    public QuoteGenerator(String[] quotes) {
        this.quotes = quotes;
    }

    // Method to generate a random quote
    public String generateQuote() {
        Random rand = new Random();
        int randomNum = rand.nextInt(quotes.length); // Generate a random number

        // Check if the new random number is different from the last one
        if (randomNum != lastRandNum) {
            lastRandNum = randomNum; // Update last random number
            return quotes[randomNum]; // Return the quote
        } else {
            return generateQuote(); // Recursively call if the same number is generated
        }
    }

    public static void main(String[] args) {
        String[] quotes = {"hi", "hello", "goodbye", "dog", "cat", "banana"};
        QuoteGenerator generator1 = new QuoteGenerator(quotes);

        // Generate and print quotes
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
        System.out.println(generator1.generateQuote());
    }
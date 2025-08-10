import java.util.Random;
import java.util.Arrays;
import java.util.List;

// Define QuoteGenerator class
public class QuoteGenerator {

    // Define class variables
    private int lastRandNum;
    private List<String> quotes;
    private Random rand;

    // Constructor
    public QuoteGenerator(List<String> quotes) {
        this.lastRandNum = -1;
        this.quotes = quotes;
        this.rand = new Random();
    }

    // Method to generate a quote
    public String generateQuote() {
        int randomNum = rand.nextInt(quotes.size());

        if (randomNum != lastRandNum) {
            lastRandNum = randomNum;
            return quotes.get(randomNum);
        } else {
            return generateQuote();
        }
    }

    // Main method
    public static void main(String[] args) {
        // Define list of quotes
        List<String> quotes = Arrays.asList("hi", "hello", "goodbye", "dog", "cat", "banana");

        // Create instance of QuoteGenerator
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
import java.util.Random;

public class QuoteGenerator {

    private Random random;
    private String[] quotes;
    private int lastRandomNumber;

    public QuoteGenerator(String[] quotes) {
        this.random = new Random();
        this.quotes = quotes;
        this.lastRandomNumber = -1;
    }

    public String generateQuote() {
        int randomNumber = random.nextInt(quotes.length);

        if (randomNumber!= lastRandomNumber) {
            lastRandomNumber = randomNumber;
            return quotes[randomNumber];
        } else {
            return generateQuote();
        }
    }
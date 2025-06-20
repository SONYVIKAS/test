import java.util.List;
import java.util.Random;

List<String> quotes = List.of("hi", "hello", "goodbye", "dog", "cat", "banana");

class QuoteGenerator {
    private int lastRandNum;
    private List<String> quotes;
    private Random random;

    public QuoteGenerator(List<String> quotes) {
        this.lastRandNum = -1;
        this.quotes = quotes;
        this.random = new Random();
    }

    public String generateQuote() {
        int randomNum = random.nextInt(quotes.size());

        while (randomNum == lastRandNum) {
            randomNum = random.nextInt(quotes.size());
        }

        lastRandNum = randomNum;
        return quotes.get(randomNum);
    }
}

QuoteGenerator generator1 = new QuoteGenerator(quotes);
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
System.out.println(generator1.generateQuote());
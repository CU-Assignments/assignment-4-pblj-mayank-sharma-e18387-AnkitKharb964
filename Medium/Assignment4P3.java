import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Card {
    private String rank;
    private String symbol;

    public Card(String rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return rank + " of " + symbol;
    }
}

public class Assignment4P3 {
    private Map<String, List<Card>> cardCollection;

    public Assignment4P3() {
        cardCollection = new HashMap<>();
    }

    public void addCard(Card card) {
        cardCollection.putIfAbsent(card.getSymbol(), new ArrayList<>());
        cardCollection.get(card.getSymbol()).add(card);
    }

    public List<Card> findCardsBySymbol(String symbol) {
        return cardCollection.getOrDefault(symbol, new ArrayList<>());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Assignment4P3 collection = new Assignment4P3();

        collection.addCard(new Card("Ace", "Hearts"));
        collection.addCard(new Card("King", "Hearts"));
        collection.addCard(new Card("Queen", "Spades"));
        collection.addCard(new Card("Jack", "Diamonds"));
        collection.addCard(new Card("Ace", "Spades"));
        collection.addCard(new Card("2", "Hearts"));

        System.out.print("Enter the symbol (e.g., Hearts, Spades) to find all cards of that symbol: ");
        String symbol = scanner.nextLine();

        List<Card> foundCards = collection.findCardsBySymbol(symbol);

        if (foundCards.isEmpty()) {
            System.out.println("No cards found for the symbol: " + symbol);
        } else {
            System.out.println("Cards of " + symbol + ":");
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }

        scanner.close();
    }
}

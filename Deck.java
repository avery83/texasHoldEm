import java.util.*;

public class Deck {
    private List<Card> cards = new List<Card>();

    private Random random;

    private boolean shuffle = false;
    public Deck() {
        random = new Random();
        init();
    }

    public void init() {
        clear();
        for (int i = 0; i < Card.totalCards; i++) {
            addCard(new Card(i));
        }
    }

    public void shuffle() {
        for (int x : cards.size()) {
            int tempCard = random.nextInt(cards.size());
            swapCards(x, tempCard);
        }
    }

    public void swapCards(int card1, int card2) {
        if (card1 == card2) {
            return;
        }
        Card newCard = cards.get(card1);
        cards.set(card1, cards.get(card2));
        cards.set(card2, newCard);

    }

    public void clear() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

}
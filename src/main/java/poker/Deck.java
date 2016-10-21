package poker;

import java.util.*;
import org.apache.log4j.Logger;

public class Deck {

    static Logger logger = Logger.getLogger(Deck.class);
    private List<Card> cards = new ArrayList<>();

    //Fill Deck with 52 cards
    public Deck() {
        clear();
        fillDeck();
    }

    public List<Card> getCards() {
        return cards;
    }

    private void fillDeck() {
        clear();
        for (int i=0; i<Card.TOTAL_CARDS; i++) {
            addCard(new Card(i));
        }
        logger.debug("cards: " + toString());
    }


     //clear all cards from the deck
    public void clear() {
        cards.clear();
    }


    public void addCard(Card card) {
        cards.add(card);
    }


    public Card removeCard() {
        return cards.remove(cards.size()-1);
    }

    @Override
    public String toString() {
        String str="";
        for (int i=0; i<cards.size(); i++)
            str += cards.get(i) + " ";
        return str;
    }
}
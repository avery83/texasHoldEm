package poker;

import java.util.*;
import org.apache.log4j.Logger;
import java.io.*;
import poker.CardRankEnum.CardRank;
import poker.SuitEnum.Suit;
/**
 *  This is the class that creates a deck of cards
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class Deck {

    static Logger logger = Logger.getLogger(Deck.class);
    private static List<Card> cards = new ArrayList<>();
    private HashMap<Card,String> mapOfCardImages;
    private Properties properties;

    /**
     *  constructor sets the Deck object
     */
    public Deck() {
        //Clear all cards from the deck
        cards.clear();
        logger.debug("Cards are cleared");
        fillDeck();
        fillImageMap();
    }

    /**
     *  fills deck with 52 cards
     */
    private void fillDeck() {
        for(Suit suit: Suit.values()) {
            for(CardRank cardRank: CardRank.values()) {
                cards.add(new Card(suit, cardRank));
            }
        }
        logger.debug("cards: " + toString());
    }
    /**
     *  maps images to cards
     */
    private void fillImageMap() {
        mapOfCardImages = new HashMap<>();
        int i = 1;
        logger.debug(cards);
        for(Card eachCard: cards) {
            mapOfCardImages.put(eachCard, "Card" + i + ".gif");
            i++;
        }
        logger.debug(mapOfCardImages);
    }

    public Card removeCard() {
        return cards.remove(cards.size()-1);
    }
    /**
     *  overrides toString method
     */
    @Override
    public String toString() {
        String str="";
        for (int i=0; i<cards.size(); i++)
            str += cards.get(i) + " ";
        return str;
    }
    public HashMap<Card, String> getMapOfCardImages() {
        return mapOfCardImages;
    }

    public static List<Card> getCards() {
        return cards;
    }
}
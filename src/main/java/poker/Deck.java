package poker;

import java.util.*;
import org.apache.log4j.Logger;
import java.io.*;
import poker.CardRankEnum.CardRank;
import poker.SuitEnum.Suit;

public class Deck {

    static Logger logger = Logger.getLogger(Deck.class);
    private static List<Card> cards = new ArrayList<>();
    private HashMap<Card,String> mapOfCardImages;
    private Properties properties;

    //Fill Deck with 52 cards
    public Deck() {
        //Clear all cards from the deck
        cards.clear();
        logger.debug("Cards are cleared");
        loadProperties();
        fillDeck();
        fillImageMap();
    }
    public HashMap<Card, String> getMapOfCardImages() {
        return mapOfCardImages;
    }

    public void loadProperties() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/deck.properties"));
        }
        catch(IOException ioe) {
            System.out.println("Can't load the properties file");
            ioe.printStackTrace();
        }
        catch(Exception e) {
            System.out.println("Problem: " + e);
            e.printStackTrace();
        }

    }

    public static List<Card> getCards() {
        return cards;
    }



    private void fillDeck() {
        for(Suit suit: Suit.values()) {
            for(CardRank cardRank: CardRank.values()) {
                cards.add(new Card(suit, cardRank));
            }
        }
        //cards.clear();
        logger.debug("cards: " + toString());
    }
    private void fillImageMap() {
        mapOfCardImages = new HashMap<>();
        int i = 1;
        for(Card eachCard: cards) {
            mapOfCardImages.put(eachCard, "Card" + i + ".gif");
            i++;
        }
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
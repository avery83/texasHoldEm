package poker;

import java.util.*;
import org.apache.log4j.Logger;
import java.io.*;

public class Deck {

    static Logger logger = Logger.getLogger(Deck.class);
    private static List<Card> cards = new ArrayList<>();
    private HashMap<Card,String> mapOfCardImages;
    private Properties properties;

    //Fill Deck with 52 cards
    public Deck() {
        logger.debug("I'm in Deck");

        //Clear all cards from the deck
        cards.clear();
        logger.debug("Cards are cleared");
        loadProperties();
        //fillDeck();

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
        fillDeck(properties);
    }

    public static List<Card> getCards() {
        return cards;
    }



    private void fillDeck(Properties properties) {
        //cards.clear();
        for (int i=0; i<Integer.valueOf(properties.getProperty("deck.size")); i++) {
            cards.add(new Card(i));
        }
        logger.debug("cards: " + toString());
        fillMap();
    }
    private void fillMap() {
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
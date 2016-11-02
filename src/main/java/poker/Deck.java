package poker;

import java.util.*;
import org.apache.log4j.Logger;
import java.io.*;

public class Deck {

    static Logger logger = Logger.getLogger(Deck.class);
    private List<Card> cards = new ArrayList<>();
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

    public List<Card> getCards() {
        return cards;
    }

    private void fillDeck(Properties properties) {
        //cards.clear();
        for (int i=0; i<Integer.valueOf(properties.getProperty("deck.size")); i++) {
            cards.add(new Card(i));
        }
        logger.debug("cards: " + toString());
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
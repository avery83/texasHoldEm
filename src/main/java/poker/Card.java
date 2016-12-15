package poker;

import org.apache.log4j.Logger;
import poker.CardRankEnum.CardRank;
import poker.SuitEnum.Suit;
/**
 *  This class creates a card
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class Card implements Comparable<Card>{

    static Logger logger = Logger.getLogger(Evaluator.class);
    private CardRank rank;
    private Suit suit;
    /**
     *  Constructor for the Card object
     */
    public Card(Suit suit, CardRank rank) {
        setCardRank(rank);
        setSuit(suit);
    }

    /**
     *  Overrides toString super method
     */
    @Override
    public String toString() {
        return rank.getSymbol().toString() + suit.getSymbol();
    }
    /**
     *  Overrides compareTo method
     */
    @Override
    public int compareTo(Card cardToCompare) {
            return rank.compareTo(cardToCompare.rank);
    }

    public CardRank getCardRank() {
        return rank;
    }

    public void setCardRank(CardRank rank) {
        this.rank = rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
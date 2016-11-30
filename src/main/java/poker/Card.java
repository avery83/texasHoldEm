package poker;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import poker.CardRankEnum.CardRank;
import poker.SuitEnum.Suit;

public class Card implements Comparable<Card>{

    static Logger logger = Logger.getLogger(Evaluator.class);
    private CardRank rank;
    private Suit suit;


    public Card(Suit suit, CardRank rank) {
        setCardRank(rank);
        setSuit(suit);
    }


    @Override
    public String toString() {
        return rank.getSymbol().toString() + suit.getSymbol();
    }

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

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
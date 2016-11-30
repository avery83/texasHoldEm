package poker;
import org.apache.log4j.Logger;

/**
 * Created by student on 11/18/16.
 */
public class SuitEnum {

    static Logger logger = Logger.getLogger(SuitEnum.class);
    public enum Suit {
        CLUB(0, 'C'),
        DIAMOND(1, 'D'),
        HEART(2, 'H'),
        SPADE(3, 'S');

        private int cardValue;
        private Character symbol;

        Suit(int cardValue, Character symbol) {
            this.cardValue = cardValue;
            this.symbol = symbol;
        }

        public int getCardValue() {

            return cardValue;
        }

        public Character getSymbol() {

            return symbol;
        }
    }
}

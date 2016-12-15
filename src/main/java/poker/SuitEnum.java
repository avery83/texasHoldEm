package poker;
import org.apache.log4j.Logger;

/**
 *  This is the enum class for the card suits
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */

public class SuitEnum {

    static Logger logger = Logger.getLogger(SuitEnum.class);
    /**
     *  enum class for the suits
     */
    public enum Suit {
        CLUB(0, 'C'),
        DIAMOND(1, 'D'),
        HEART(2, 'H'),
        SPADE(3, 'S');

        private int cardValue;
        private Character symbol;
        /**
         *  constructor for the suit enum
         *  @param cardValue
         *  @param symbol
         */
        Suit(int cardValue, Character symbol) {
            this.cardValue = cardValue;
            this.symbol = symbol;
        }

        public Character getSymbol() {
            return symbol;
        }
    }
}

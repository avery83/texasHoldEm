package poker;

/**
 * Created by student on 11/18/16.
 */
public class CardRankEnum {
    public enum CardRank {
        TWO(2, '2'),
        THREE(3, '3'),
        FOUR(4, '4'),
        FIVE(5, '5'),
        SIX(6, '6'),
        SEVEN(7, '7'),
        EIGHT(8, '8'),
        NINE(9, '9'),
        TEN(10, 'T'),
        JACK(11, 'J'),
        QUEEN(12, 'Q'),
        KING(13, 'K'),
        ACE(14, 'A');

        private int cardValue;
        private Character symbol;

        CardRank(int cardValue, Character symbol) {
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

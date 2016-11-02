package poker;

public class Card implements Comparable<Card>{

    //public static final int TOTAL_CARDS = 52;


    public enum Suit {
        CLUB(0, 'C'),
        DIAMOND(1, 'D'),
        HEART(2, 'H'),
        SPADE(3, 'S');

        private int cardValue;
        private Character symbol;

        private static Suit[] cardsSuits = {SPADE, HEART, DIAMOND, CLUB};

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

        static public Suit getSuit(int suit) {

            return cardsSuits[suit];
        }
    }

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

        private final int cardValue;
        private final Character symbol;

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

        private static final CardRank [] cardRanks = {null, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
                EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};

        // both 1 and 14 are recognized as ACE
        public static CardRank getCardRank(int cardValue) {

            return cardRanks[cardValue];
        }
    }

    private CardRank rank;
    private Suit suit;


    public Card(int cardValue) {
        rank = CardRank.getCardRank( cardValue%13 + 1 );
        suit = Suit.getSuit( cardValue/13 );
    }


    @Override
    public String toString() {
        return rank.getSymbol().toString() + suit.getSymbol();
    }

    @Override
    public int compareTo(Card cardToCompare) {
        int ranking = rank.compareTo(cardToCompare.rank);
        if (ranking != 0)
            return ranking;
        else
            return suit.compareTo(cardToCompare.suit);
    }

    @Override
    public boolean equals(Object obj) {
        Card card2 = (Card) obj;
        if (rank == card2.rank && suit == card2.suit)
            return true;
        else
            return false;
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

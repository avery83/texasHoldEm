public class Rank {
    public enum handRank {

        HIGH_CARD(0, "high card"),
        PAIR(1, "pair"),
        TWO_PAIR(2, "two pair"),
        THREE_OF_A_KIND(3, "three of a kind"),
        STRAIGHT(4, "straight"),
        FLUSH(5, "flush"),
        FULL_HOUSE(6, "full house"),
        FOUR_OF_A_KIND(7, "four of a kind"),
        STRAIGHT_FLUSH(8, "straight flush"),
        ROYAL_FLUSH(9, "royal flush");

        static public int totalHandRanks = 10;

        private int handValue;
        private String myString;

        static private handRank[] handRankMap = {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND,
                STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH};

        private handRank(int handValue, String myString) {
            this.handValue = handValue;
            this.myString = myString;
        }

        public int getHandValue() {
            return handValue;
        }

        @Override
        public String toString() {
            return string;
        }

        static public handRank getHandRank(int handValue) {
            return handRankMap[handValue];
        }
    }

    private HandRank handRank = HandRank.HIGH_CARD;

    private ArrayList<Card> highCards = new ArrayList<Card>();

    @Override
    public String toString() {
        return pattern.toString() + ", " + highCards.toString();
    }

    @Override
    public int compareTo(Rank newRank) {
        int compareRank = handRank.compareTo(newScore.handRank);
        if (compareRank != 0)
            return compareRank;
        else
            return compareHighCards(newRank);
    }

    @Override
    public boolean equals(Object obj) {
        Rank newRank = (Rank) obj;
        if ( handRank == newRank.handRank && compareHighCards(newRank) == 0 )
            return true;
        else
            return false;
    }
}
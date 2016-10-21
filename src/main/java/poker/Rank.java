package poker;

import java.util.*;

public class Rank implements Comparable<Rank>  {

    public enum Ranking {

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

        private int rankValue;
        private String rankString;

        static private final Ranking [] handRanks = { HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND,
                STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH };

        Ranking(int rankValue, String rankString) {
            this.rankValue = rankValue;
            this.rankString = rankString;
        }

        public int getIntValue() {
            return rankValue;
        }

        @Override
        public String toString() {
            return rankString;
        }

        static public Ranking getRanking(int rankValue) {
            return handRanks[rankValue];
        }

    }

    private Ranking ranking = Ranking.HIGH_CARD;

    private ArrayList<Card> highCards = new ArrayList<>();

    @Override
    public String toString() {
        return ranking.toString() + ", " + highCards.toString();
    }

    @Override
    public int compareTo(Rank rankToCompare) {
        int rankValue = ranking.compareTo(rankToCompare.ranking);
        if (rankValue != 0)
            return rankValue;
        else
            return compareHighCards(rankToCompare);
    }

    @Override
    public boolean equals(Object obj) {
        Rank rank = (Rank) obj;
        if ( ranking == rank.ranking && compareHighCards(rank)== 0 )
            return true;
        else
            return false;
    }

    private int compareHighCards(Rank rank) {
        for (int i=0; i<highCards.size(); i++) {
            if ( highCards.get(i).getRank().getCardValue() < rank.highCards.get(i).getRank().getCardValue() )
                return -1;
            else if ( highCards.get(i).getRank().getCardValue() > rank.highCards.get(i).getRank().getCardValue() )
                return 0xffff;
        }
        return 0;
    }


    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public ArrayList<Card> getHighCards() {
        return highCards;
    }

    public void setHighCards(ArrayList<Card> highCards) {
        this.highCards = highCards;
    }
}

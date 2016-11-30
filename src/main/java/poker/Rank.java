package poker;

import java.util.*;

public class Rank implements Comparable<Rank>  {

    public enum Score {

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


        Score(int rankValue, String rankString) {
            this.rankValue = rankValue;
            this.rankString = rankString;
        }

        @Override
        public String toString() {
            return rankString;
        }
    }

    public Score score = Score.HIGH_CARD;
    private ArrayList<Card> highCards = new ArrayList<>();

    @Override
    public String toString() {
        return score.toString() + ", " + highCards.toString();
    }

    //Compare ranks
    @Override
    public int compareTo(Rank rankToCompare) {
        int rankValue = score.compareTo(rankToCompare.score);
        if (rankValue != 0)
            return rankValue;
        else
            return compareHighCards(rankToCompare);
    }

    @Override
    public boolean equals(Object obj) {
        Rank rank = (Rank) obj;
        if ( score == rank.score && compareHighCards(rank)== 0 )
            return true;
        else
            return false;
    }

    private int compareHighCards(Rank rank) {
        for (int i=0; i<highCards.size(); i++) {
            if ( highCards.get(i).getCardRank().getCardValue() < rank.highCards.get(i).getCardRank().getCardValue() )
                return -1;
            else if ( highCards.get(i).getCardRank().getCardValue() > rank.highCards.get(i).getCardRank().getCardValue() )
                return 1;
        }
        return 0;
    }

    public void setRanking(Score ranking) {
        this.score = ranking;
    }


    public ArrayList<Card> getHighCards() {
        return highCards;
    }
}
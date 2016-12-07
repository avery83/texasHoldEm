package poker;

import java.util.*;
import poker.ScoreEnum.Score;

public class Rank implements Comparable<Rank>  {



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
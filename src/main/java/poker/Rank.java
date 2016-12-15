package poker;

import java.util.*;
import poker.ScoreEnum.Score;
/**
 *  This class ranks the scores and compares the highcards
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class Rank implements Comparable<Rank>  {

    public Score score = Score.HIGH_CARD;
    private ArrayList<Card> highCards = new ArrayList<>();
    /**
     *  overrides toString method
     */
    @Override
    public String toString() {
        return score.toString() + ", " + highCards.toString();
    }

    /**
     *  overrides compareTo methods
     *  @param rankToCompare
     */
    @Override
    public int compareTo(Rank rankToCompare) {
        int rankValue = score.compareTo(rankToCompare.score);
        if (rankValue != 0)
            return rankValue;
        else
            return compareHighCards(rankToCompare);
    }
    /**
     *  overides equals method
     *  @param  obj
     */
    @Override
    public boolean equals(Object obj) {
        Rank rank = (Rank) obj;
        if ( score == rank.score && compareHighCards(rank)== 0 )
            return true;
        else
            return false;
    }
    /**
     *  compares cards by rank
     *  @param  rank
     *  @return int
     */
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
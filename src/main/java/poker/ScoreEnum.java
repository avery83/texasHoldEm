package poker;

/**
 *  This is the enum that holds the Scores
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class ScoreEnum {
    /**
     *  enum class that holds the scores
     */
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
        /**
         *  constructor for score enum
         */
        Score(int rankValue, String rankString) {
            this.rankValue = rankValue;
            this.rankString = rankString;
        }
        /**
         *  overrides toString method
         *  @return string
         */
        @Override
        public String toString() {
            return rankString;
        }
    }
}

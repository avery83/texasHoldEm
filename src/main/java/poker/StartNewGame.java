package poker;

import java.util.ArrayList;
import poker.Deal.*;
import poker.Entity.Game;

import java.util.*;

/**
 * Created by student on 11/3/16.
 */
public class StartNewGame {
/*
    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.setUserName("Ashley");
        newGame.setNumberOfPlayers(4);
        newGame.setStartingChips(1000.00);
        Deal dealer = new Deal();

        while (dealer.getStateOfHand() != StateOfHand.RIVER) {
            dealer.deal();
        }

        System.out.println(dealer.toString());

        // evaluate and rank each hand
        dealer.scoreHands();
        List<Rank> scoreList = dealer.getScoreList();
        Comparator cmp = Collections.reverseOrder();
        Collections.sort(scoreList, cmp);
        String winner = "";
        //ArrayList<Card> playerCards = HumanPlayer;
        int i = 0;
        for(Rank score: scoreList) {
            //HumanPlayer eachPlayer = dealer.getPlayerByScore(score);
            if (i == 0) {
                winner = dealer.getPlayerByScore(score).getPlayerName();
            }
            int rank = dealer.getRankByScore(score);
            System.out.println("rank: " + rank + ", " + (dealer.getPlayerByScore(score).getPlayerName()) +
                    ", score: " + score.toString());
            i++;
        }
        System.out.println("Winner: " + winner);

    }
*/
}
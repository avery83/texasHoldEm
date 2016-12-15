package poker;

import org.apache.log4j.Logger;
/**
 *  This class creates the action
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class Action {
    private Deal currentDeal;
    private HumanPlayer player;
    private Double bet;
    private String move;

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     *  Constructor for Action object
     *@param currentDeal
     *@param player
     */
    public Action(Deal currentDeal, HumanPlayer player) {
        this.currentDeal = currentDeal;
        this.player = player;
    }

    /**
     *  move method calls all in for now
     */
    public void move() {
            allIn();
        }


    /**
     *  method to go all in
     */
    public void allIn() {
        double raise = 0.0;
        raise = player.getPlayersStartingChips();
        currentDeal.setPot(currentDeal.getPot() + player.getPlayersStartingChips());
        player.setPlayersStartingChips(player.getPlayersStartingChips() - player.getPlayersStartingChips());
        currentDeal.setBet(raise);
        this.setBet(raise);
        currentDeal.setTotalBets(currentDeal.totalBets + raise);
        player.getPlayersActions().add(this);
        this.setMove("ALL IN");
        currentDeal.listOfMoves.add(this.getMove());
        currentDeal.playersAllIn.add(player);
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

}

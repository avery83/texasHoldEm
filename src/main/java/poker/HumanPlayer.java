package poker;

import java.util.HashMap;
import poker.Entity.Game;

/**
 * Created by student on 10/3/16.
 */
public class HumanPlayer {
    HashMap<String,Card> playersHoleCards;
    private String playerName;
    private double playersStartingChips;

    public double getPlayersStartingChips() {
        return playersStartingChips;
    }

    public void setPlayersStartingChips(double playersStartingChips) {
        this.playersStartingChips = playersStartingChips;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public HashMap<String, Card> getPlayersHoleCards() {
        return playersHoleCards;
    }

    public HumanPlayer() {
        playersHoleCards = new HashMap<>();
        playerName = this.getPlayerName();
        //playersStartingChips = currentGame.getStartingChips();
    }
}

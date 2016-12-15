package poker;

import java.util.HashMap;
import java.util.List;
import java.util.*;
/**
 *  This class creates opponents. not sure why it is called humanplayer
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class HumanPlayer {
    HashMap<String,Card> playersHoleCards;
    private String playerName;
    private double playersStartingChips;
    private List<Action> playersActions;

    /**
     *  constructor to setup HumanPlayer object
     */
    public HumanPlayer() {
        playersHoleCards = new HashMap<>();
        playerName = this.getPlayerName();
        playersActions = new ArrayList<>();
    }

    public List<Action> getPlayersActions() {
        return playersActions;
    }

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
}

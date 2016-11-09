package poker;

import java.util.HashMap;

/**
 * Created by student on 10/3/16.
 */
public class HumanPlayer {
    HashMap<String,Card> playersHoleCards;
    String playerName;

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
    }
}

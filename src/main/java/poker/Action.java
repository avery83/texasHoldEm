package poker;

import org.apache.log4j.Logger;
import poker.Deal.*;
/**
 * Created by student on 11/29/16.
 */
public class Action {
    Deal currentDeal;
    HumanPlayer player;
    private final Logger logger = Logger.getLogger(this.getClass());

    public Action(Deal currentDeal, HumanPlayer player) {
        this.currentDeal = currentDeal;
        this.player = player;

    }

    public void move(Double bet) {
        int random = (int )(Math.random() * 3 + 1);
        if (random == 1) {
            raise();
        }
        if (random == 2) {
            check(bet);
        }
        if (random == 3) {
            fold();
        }

    }

    public void raise() {
        currentDeal.setPot(currentDeal.getPot() + player.getPlayersStartingChips());

        player.setPlayersStartingChips(player.getPlayersStartingChips() - player.getPlayersStartingChips());


        logger.debug(currentDeal.getPot());
        logger.debug(player.getPlayersStartingChips());

    }

    public void check(Double bet) {
        currentDeal.setPot(currentDeal.getPot() + bet);

        player.setPlayersStartingChips(player.getPlayersStartingChips() - bet);
        logger.debug(currentDeal.getPot());
        logger.debug(player.getPlayersStartingChips());
    }

    public void fold() {
        logger.debug("we fold");
    }
}

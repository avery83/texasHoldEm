package poker;

import poker.Deal.*;
/**
 * Created by student on 11/29/16.
 */
public class Action {
    Deal currentHand;

    public Action(Deal currentHand) {
        this.currentHand = currentHand;

    }

    public void move() {

    }

    public void raise() {

        currentHand.getPot();

    }

    public void check() {

    }

    public void fold() {

    }
}

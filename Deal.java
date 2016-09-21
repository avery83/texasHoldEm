public class Deal {

    private Deck deck;

    private int totalHands;

    private List<Card> hand;

    private List<Card> table = new Card(5);

    enum DealState {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER;
    }

    private DealState dealState = DealState.CLEAR;

    public Deal() {
        deck = new Deck();
        setTotalHands(9);
    }

    public void deal() {
        switch (dealState) {

            case CLEAR:
                newDeal();
                break;

            case PREFLOP:
                flop();
                break;

            case FLOP:
                turn();
                break;

            case TURN:
                river();
                break;

            case RIVER:
                clear();
                break;
        }
    }


    private void flop() {
        for (int i=0; i<3; i++) {
            table[i] = deck.removeCard();
        }
        dealState = DealState.FLOP;
        setChanged();
    }

    private void newDeal() {
        deck.shuffle();
        dealHands();
        dealState = DealState.PREFLOP;
        setChanged();
    }

    private void dealHands() {
        for (int i=0; i<2; i++) {
            for (int j=0; j<totalHands; j++) {
                hand[j][i] = deck.removeCard();
            }
        }
        setChanged();
    }

    private void turn() {
        table[3] = deck.removeCard();
        dealState = DealState.TURN;
        setChanged();
    }

    private void river() {
        table[4] = deck.removeCard();
        dealState = DealState.RIVER;
        setChanged();
    }

    public void setTotalHands(int hands) {
        this.totalHands = hands;
        hand = new Card[getTotalHands()][2];
    }

    public boolean isCompleted() {
        return dealState == DealState.RIVER;
    }
}

package poker;
import org.apache.log4j.*;
import java.util.*;
import poker.Card;
import poker.Deck;


public class Deal {

    private final Logger logger = Logger.getLogger(Deal.class.getName());
    private Deck deck;
    private int numberOfPlayers;
    private Card[][] holeCards;
    private Card[] communityCards = new Card[5];
    private StateOfHand stateOfHand;

    /**
     * The state of the dealer.
     * When in RIVER, the card dealing process is complete.
     */
    enum StateOfHand {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }

    private boolean scored = false;

    public Deal() {
        // default deck
        logger.debug("Trace Message!");
        stateOfHand = StateOfHand.CLEAR;
        deck = new Deck();
        setNumberOfPlayers(4);
    }

    /**
     * performs one step of the card dealing process
     */
    public void deal() {
        switch (stateOfHand) {
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

    private void newDeal() {
        Collections.shuffle(deck.getCards());
        logger.debug("shuffle: " + deck.toString());
        dealHands();
        stateOfHand = StateOfHand.PREFLOP;
    }

    private void dealHands() {
        for (int i=0; i<2; i++) {
            for (int j=0; j<numberOfPlayers; j++) {
                holeCards[j][i] = deck.removeCard();
            }
        }
    }

    private void flop() {
        for (int i=0; i<3; i++) {
            communityCards[i] = deck.removeCard();
        }
        stateOfHand = StateOfHand.FLOP;
    }

    private void turn() {
        communityCards[3] = deck.removeCard();
        stateOfHand = StateOfHand.TURN;
    }

    private void river() {
        communityCards[4] = deck.removeCard();
        stateOfHand = StateOfHand.RIVER;
    }

    public void clear() {
        for (int i=0; i<getNumberOfPlayers(); i++) {
            Arrays.fill(holeCards[i], null);// maybe remove for loop
        }

        Arrays.fill(communityCards, null);

        stateOfHand = StateOfHand.CLEAR;
        scored = false;
    }

    @Override
    public String toString() {

        String str="";
        for (int i=0; i<5; i++) {
            if (communityCards[i]!=null)
                str += communityCards[i].toString() + ' ';
        }

        String NL = System.getProperty("line.separator");
        str += NL;

        for (int i=0; i<numberOfPlayers; i++) {
            if (holeCards[i][0]!=null) {
                str += (i+1) + ": " + holeCards[i][0].toString() + ' ';
                str += holeCards[i][1].toString() + "  ";
            }
        }

        return str;
    }

    public void setNumberOfPlayers(int hands) {
        this.numberOfPlayers = hands;
        holeCards = new Card[getNumberOfPlayers()][2];
    }

    public boolean isCompleted() {
        return stateOfHand == StateOfHand.RIVER;
    }

    private ArrayList<Rank> scoreList = new ArrayList<>();
    private HashMap<Integer, Rank> handToScoreMap = new HashMap<>();
    private HashMap<Rank, Integer> scoreToHandMap = new HashMap<>();
    private HashMap<Rank, Integer> scoreToRankMap = new HashMap<>();
    private HashMap<Rank, Boolean> scoreToTiedMap = new HashMap<>();

    public void scoreHands() {
        scoreList.clear();
        Evaluator evaluator = new Evaluator();
        for (int i=0; i<getNumberOfPlayers(); i++) {

            Card [] cards = getCombinedCards(i);
            Rank score = evaluator.evaluate(cards);
            scoreList.add( score );
            handToScoreMap.put(i, score);
            scoreToHandMap.put(score, i);
        }

        // sort the scores
        Collections.sort(scoreList);

        // rank the scores
        boolean equalPrevious;
        boolean equalNext = false;
        int equalRank = 1;
        Rank score;
        Rank scoreNext;

        for (int i=0; i<getNumberOfPlayers(); i++) {
            int rank = i+1;
            score = scoreList.get(getNumberOfPlayers()-i-1);
            equalPrevious = equalNext;
            // compare with next score to detect tie
            if (getNumberOfPlayers()-i-1 != 0) {
                scoreNext = scoreList.get(getNumberOfPlayers()-i-2);
                if (score.equals(scoreNext)) {
                    // tied with next hand

                    // set equal rank, if the starting rank of the tie
                    if (!equalPrevious)
                        equalRank = rank;

                    equalNext = true;

                } else {
                    equalNext = false;

                }

            } else
                equalNext = false;

            if (equalPrevious)	{
                // mark from previous hand
                rank = equalRank;
            }

            scoreToRankMap.put(score, rank);
            scoreToTiedMap.put(score, equalPrevious || equalNext);
        }

        scored = true;

        //setChanged();
    }

    private Card [] getCombinedCards(int hand) {
        Card [] cards = new Card[7];
        for (int j=0; j<5; j++) {
            cards[j] = getCommunityCards()[j];
        }
        for (int j=0; j<2; j++) {
            cards[5+j] = getHoleCards()[hand][j];
        }
        return cards;
    }

    ////////////////////////////////
    // getters and setters

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Card[][] getHoleCards() {
        return holeCards;
    }

    public void setHoleCards(Card[][] holeCards) {
        this.holeCards = holeCards;
    }

    public Card[] getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(Card[] communityCards) {
        this.communityCards = communityCards;
    }

    public StateOfHand getStateOfHand() {
        return stateOfHand;
    }

    // scoreList is sorted according to natural order
    public ArrayList<Rank> getScoreList() {
        return scoreList;
    }

    public Rank getScoreByHand(int hand) {
        return handToScoreMap.get(hand);
    }

    public int getHandByScore(Rank score) {
        return scoreToHandMap.get(score);
    }

    public int getRankByScore(Rank score) {
        return scoreToRankMap.get(score);
    }

    public boolean isTied(Rank score) {
        return scoreToTiedMap.get(score);
    }

    public boolean isScored() {
        return scored;
    }

    /////////////////////////////////////////////////
    // test

    public static void main(String[] args) {

        Deal dealer = new Deal();

        while (!dealer.isCompleted())
            dealer.deal();

        System.out.println(dealer.toString());

        // evaluate and rank each hand
        dealer.scoreHands();

        ArrayList<Rank> scoreList = dealer.getScoreList();

        for (int i=0; i<dealer.getNumberOfPlayers(); i++) {

            Rank score = scoreList.get(dealer.getNumberOfPlayers()-i-1);

            int hand = dealer.getHandByScore(score);
            int rank = dealer.getRankByScore(score);
            boolean isTied = dealer.isTied(score);

            System.out.println("rank: " + rank + ", hand: " + (hand+1) +
                    ", score: " + score.toString() + ( isTied? " (tied)" : "") );
        }

    }
}

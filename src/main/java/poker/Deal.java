package poker;
import org.apache.log4j.*;
import java.util.*;
import java.util.ListIterator;
import poker.Card;
import poker.Deck;


public class Deal {

    private final Logger logger = Logger.getLogger(Deal.class.getName());
    private Deck deck;
    private int numberOfPlayers;
    private List<HumanPlayer> playersInGame;
    private Card[] communityCards = new Card[5];
    private StateOfHand stateOfHand;
    private HumanPlayer newPlayer;

    
    enum StateOfHand {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }

    //private boolean scored = false;

    public Deal() {
        logger.debug("Trace Message!");
        stateOfHand = StateOfHand.CLEAR;
        deck = new Deck();
        playersInGame = new ArrayList<>();
        setNumberOfPlayers(4);
    }

    
    public void deal() {
        logger.debug("This is where I left off");
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
                clearDeck();
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
        for (int i=0; i<numberOfPlayers; i++) {
            newPlayer = new HumanPlayer();
            for (int j=0; j < 2; j++) {
                String cardPosition;
                cardPosition = "Hole Card #" + (j +1);
                newPlayer.playersHoleCards.put(cardPosition,deck.removeCard());
            }
            playersInGame.add(newPlayer);
        }
    }

    private void flop() {
        for (int i=0; i<3; i++) {
            communityCards[i] = deck.removeCard();
            logger.debug(communityCards[i].getCardRank().getSymbol());
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

    public void clearDeck() {
        playersInGame.clear();
        Arrays.fill(communityCards, null);
        stateOfHand = StateOfHand.CLEAR;
        //scored = false;
    }

    @Override
    public String toString() {
        Card card1;
        Card card2;
        int b = 0;

        String str="";
        for (int i=0; i<5; i++) {
            if (communityCards[i]!=null)
                str += communityCards[i].toString() + ' ';
        }

        String NL = System.getProperty("line.separator");
        str += NL;

        for (HumanPlayer eachPlayer:playersInGame) {
                card1 = eachPlayer.playersHoleCards.get("Hole Card #1");
                card2 = eachPlayer.playersHoleCards.get("Hole Card #2");
                str += "Player " + (b+1) + ": " + card1 + ' ';
                str += card2 + "  ";
            b++;
        }
        return str;
    }

    public void setNumberOfPlayers(int players) {

        //logger.debug(HumanPlayer.playersHoleCards);
        this.numberOfPlayers = players;
        for(int i=0; i<players;i++){

        }
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
        int p = 0;
        scoreList.clear();
        Evaluator evaluator = new Evaluator();
        for(HumanPlayer eachPlayer: playersInGame) {
        //for (int i=0; i<getNumberOfPlayers(); i++) {
            //List<Card> cards = getCombinedCards(eachPlayer);
            Card [] cards = getCombinedCards(eachPlayer);
            Rank score = evaluator.evaluate(cards);
            scoreList.add( score );
            handToScoreMap.put(p, score);
            scoreToHandMap.put(score, p);
            p++;
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

        //scored = true;

        //setChanged();
    }

    private Card [] getCombinedCards(HumanPlayer player) {
        int q = 0;
        Card [] cards = new Card[7];
        for (int j=0; j<5; j++) {
            cards[j] = getCommunityCards()[j];
        }
        for (String key: player.playersHoleCards.keySet()) {
        //for (int j=0; j<2; j++) {
            cards[5+q] = player.playersHoleCards.get(key);
                    //getHoleCards()[hand][j];
            q++;
        }
        return cards;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
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

    //public boolean isScored() {
        //return scored;
    //}

    public static void main(String[] args) {

        Deal dealer = new Deal();

        while (!dealer.isCompleted())
            dealer.deal();

        //System.out.println(playersInGame);
        System.out.println(dealer.toString());

        // evaluate and rank each hand
        dealer.scoreHands();

        ArrayList<Rank> scoreList = dealer.getScoreList();


        for (int i=0; i<dealer.getNumberOfPlayers(); i++) {

            Rank score = scoreList.get(dealer.getNumberOfPlayers()-i-1);

            int hand = dealer.getHandByScore(score);
            int rank = dealer.getRankByScore(score);
            boolean isTied = dealer.isTied(score);

            System.out.println("rank: " + rank + ", player: " + (hand+1) +
                    ", score: " + score.toString() + ( isTied? " (tied)" : "") );
        }
    }
}

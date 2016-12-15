package poker;

import poker.Entity.Game;
import poker.Entity.Users;
import org.apache.log4j.*;
import poker.persistence.AbstractDao;
import poker.persistence.UserDao;

import java.util.*;
/**
 *  This is the class that deals the cards
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
public class Deal {

    private final Logger logger = Logger.getLogger(Deal.class.getName());
    private Deck deck;
    private int numberOfPlayers;
    private List<HumanPlayer> playersInGame;
    private List<Card> communityCards = new ArrayList<>(5);
    private StateOfHand stateOfHand;
    private HumanPlayer newPlayer;
    private List<Rank> scoreList;
    private HashMap<Rank, HumanPlayer> scoreToPlayerMap;
    private HashMap<Rank, Integer> scoreToRankMap;
    private double pot;
    private Action action;
    private Game currentGame;
    private double smallBlind;
    private double bigBlind;
    private int handsPlayed;
    private Users currentUser;
    private double startingChips;
    Double bet;
    Double totalBets;
    boolean roundOfBettingCompleted;
    List<String> listOfMoves;
    List<HumanPlayer> playersAllIn;

    /**
     *  enum that declares the state of hand
     */
    enum StateOfHand {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }

    /**
     *  Constructor for the Deal object
     */
    public Deal(int id, String userName) {
        AbstractDao<Game> dao2 = new AbstractDao(Game.class);
        UserDao dao = new UserDao();
        currentUser = dao.getUser(userName);
        currentGame = dao2.get(id);
        numberOfPlayers = currentGame.getNumberOfPlayers();
        this.setStartingChips(currentGame.getStartingChips());
        playersAllIn = new ArrayList<>();
        this.setBigBlind(bigBlind = currentGame.getStartingChips() / 20);
        smallBlind = bigBlind / 2;
        handsPlayed=0;
        bet = 0.00;
        totalBets = 0.00;
        roundOfBettingCompleted = false;
        stateOfHand = StateOfHand.CLEAR;
        deck = new Deck();
        playersInGame = new ArrayList<>();
    }

    /**
     *  deal method that uses switch
     */
    public void deal() {
        switch (stateOfHand) {
            case CLEAR:
                //players bet
                newDeal();
                break;
            case PREFLOP:
                flop();
                //players bet
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


    /**
     *  shuffles and deals hands
     */
    private void newDeal() {
        //Shuffle deck three times
        Collections.shuffle(deck.getCards());
        Collections.shuffle(deck.getCards());
        Collections.shuffle(deck.getCards());
        pot = 0.00;
        dealHands();
        //set state of hand to preflop
        stateOfHand = StateOfHand.PREFLOP;
    }
    /**
     *  deals hands
     */
    private void dealHands() {
        newPlayer = new HumanPlayer();
        newPlayer.setPlayerName(currentUser.getUserName());
        newPlayer.setPlayersStartingChips(startingChips);
        playersInGame.add(newPlayer);

        for (int i=1; i< numberOfPlayers; i++) {
            newPlayer = new HumanPlayer();
            newPlayer.setPlayerName("player" + (i + 1));
            playersInGame.add(newPlayer);
        }
        for (HumanPlayer eachPlayer: playersInGame) {
            for (int j=0; j < 2; j++) {
                String cardPosition;
                cardPosition = "Hole Card #" + (j +1);
                eachPlayer.playersHoleCards.put(cardPosition,deck.removeCard());
            }
        }
        makeMove();
    }
    /**
     *  creates an action for each move
     */
    private void makeMove() {
        listOfMoves = new ArrayList<>();
        for (int i = 1; i < playersInGame.size();i++) {
            playersInGame.get(i).setPlayersStartingChips(startingChips);
            Action action = new Action(this, playersInGame.get(i));
            action.move();
        }
    }
    /**
     *  deals first three community cards
     */
    private void flop() {
        for (int i=0; i<3; i++) {
            communityCards.add(deck.removeCard());
        }
        stateOfHand = StateOfHand.FLOP;
    }
    /**
     *  deals fourth community card
     */
    private void turn() {
        communityCards.add(deck.removeCard());
        stateOfHand = StateOfHand.TURN;
    }
    /**
     *  deals fifth card
     */
    private void river() {
        communityCards.add(deck.removeCard());
        stateOfHand = StateOfHand.RIVER;
    }
    /**
     *  clears the deck
     */
    public void clearDeck() {
        playersInGame.clear();
        communityCards.clear();
        stateOfHand = StateOfHand.CLEAR;
    }

    /**
     *  initializes score collections
     */
    public void scoreHands() {
        scoreList = new ArrayList<>();
        scoreToPlayerMap = new HashMap<>();
        scoreToRankMap = new HashMap<>();
        getScoreList().clear();
        addScoreToCollections();
        // sort the scores
        Collections.sort(getScoreList());
    }
    /**
     *  add scores to collections to be evaluated
     */
    public void addScoreToCollections() {
        int rank = 1;
        Evaluator evaluator = new Evaluator();
        for (HumanPlayer eachPlayer : playersInGame) {
            ArrayList<Card> cards = getCombinedCards(eachPlayer);
            Rank score = evaluator.evaluate(cards);
            getScoreList().add(score);
            scoreToPlayerMap.put(score, eachPlayer);
            scoreToRankMap.put(score, rank);
            rank++;
        }
    }

    private ArrayList<Card> getCombinedCards(HumanPlayer player) {
        ArrayList<Card> cards = new ArrayList<>(7);
        cards.addAll(getCommunityCards());

        for (String key: player.playersHoleCards.keySet()) {
            cards.add(player.playersHoleCards.get(key));
        }
        return cards;
    }

    //Getters and Setters
    public List<HumanPlayer> getPlayersInGame() {
        return playersInGame;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public StateOfHand getStateOfHand() {
        return stateOfHand;
    }

    public List<Rank> getScoreList() {
        return scoreList;
    }

    public HumanPlayer getPlayerByScore(Rank score) {
        return scoreToPlayerMap.get(score);
    }

    public int getRankByScore(Rank score) {
        return scoreToRankMap.get(score);
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public void setTotalBets(Double totalBets) {
        this.totalBets = totalBets;
    }

    public void setBigBlind(double bigBlind) {
        this.bigBlind = bigBlind;
    }

    public double getStartingChips() {
        return startingChips;
    }

    public void setStartingChips(double startingChips) {
        this.startingChips = startingChips;
    }
}

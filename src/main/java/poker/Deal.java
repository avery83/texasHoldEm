package poker;

import poker.Entity.Game;
import poker.Entity.Users;
import org.apache.log4j.*;
import poker.persistence.AbstractDao;

import java.util.*;

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



    enum StateOfHand {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }


    public Deal(int id) {
        AbstractDao<Game> dao2 = new AbstractDao(Game.class);
        currentGame = dao2.get(id);

        bigBlind = currentGame.getStartingChips() / 20;
        smallBlind = bigBlind / 2;
        logger.debug(bigBlind);
        //currentGame = newGame;
        logger.debug("New Deal");
        stateOfHand = StateOfHand.CLEAR;
        deck = new Deck();
        playersInGame = new ArrayList<>();
        action = new Action(this);

        //setNumberOfPlayers(4);
    }


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



    private void newDeal() {
        //Shuffle deck three times
        Collections.shuffle(deck.getCards());
        Collections.shuffle(deck.getCards());
        Collections.shuffle(deck.getCards());
        logger.debug("shuffle: " + deck.toString());
        pot = 0.00;
        dealHands();
        //players bet
        //set state of hand to preflop
        stateOfHand = StateOfHand.PREFLOP;
    }

    private void dealHands() {


        for (int i=0; i< 4; i++) {

            newPlayer = new HumanPlayer();
            newPlayer.setPlayerName("Player: " + (i+1));
            for (int j=0; j < 2; j++) {
                String cardPosition;
                cardPosition = "Hole Card #" + (j +1);
                newPlayer.playersHoleCards.put(cardPosition,deck.removeCard());
            }
            getAction().move();
            //newplayer.move
            playersInGame.add(newPlayer);//stay here
        }
    }

    private void flop() {
        for (int i=0; i<3; i++) {
            communityCards.add(deck.removeCard());
        }
        for(HumanPlayer player: playersInGame) {
            getAction().move();
        }
        stateOfHand = StateOfHand.FLOP;
    }

    private void turn() {
        communityCards.add(deck.removeCard());
        for(HumanPlayer player: playersInGame) {
            getAction().move();
        }
        stateOfHand = StateOfHand.TURN;
    }

    private void river() {
        communityCards.add(deck.removeCard());
        for(HumanPlayer player: playersInGame) {
            getAction().move();
        }
        stateOfHand = StateOfHand.RIVER;
    }

    public void clearDeck() {
        playersInGame.clear();
        communityCards.clear();
        stateOfHand = StateOfHand.CLEAR;
    }

    @Override
    public String toString() {
        Card card1;
        Card card2;
        int b = 0;

        String str="";
        for(Card communityCard: communityCards) {
            if (communityCard!=null)
                str += communityCard.toString() + ' ';
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


    //private HashMap<HumanPlayer, Rank> playerToScoreMap = new HashMap<>();



    public void scoreHands() {
        scoreList = new ArrayList<>();
        scoreToPlayerMap = new HashMap<>();
        scoreToRankMap = new HashMap<>();
        getScoreList().clear();
        addScoreToCollections();
        // sort the scores
        Collections.sort(getScoreList());

        //Rank score;
        //for (int i=0; i<getNumberOfPlayers(); i++) {
            //int rank = i+1;
            //score = scoreList.get(getNumberOfPlayers()-i-1);
            ///scoreToRankMap.put(score, rank);
        //}
    }

    public void addScoreToCollections() {
        int rank = 1;
        Evaluator evaluator = new Evaluator();
        for (HumanPlayer eachPlayer : playersInGame) {
            ArrayList<Card> cards = getCombinedCards(eachPlayer);
            Rank score = evaluator.evaluate(cards);
            getScoreList().add(score);
            //playerToScoreMap.put(eachPlayer, score);
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

    public void setPlayersInGame(List<HumanPlayer> playersInGame) {
        this.playersInGame = playersInGame;
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

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
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
}

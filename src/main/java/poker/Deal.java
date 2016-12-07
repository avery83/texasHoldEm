package poker;

import poker.Entity.Game;
import poker.Entity.Users;
import org.apache.log4j.*;
import poker.persistence.AbstractDao;
import poker.persistence.UserDao;

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
    private int handsPlayed;
    private Users currentUser;
    private double startingChips;

    public double getStartingChips() {
        return startingChips;
    }

    public void setStartingChips(double startingChips) {
        this.startingChips = startingChips;
    }

    private double bet;

    public int getHandsPlayed() {
        return handsPlayed;
    }

    public void setHandsPlayed(int handsPlayed) {
        this.handsPlayed = handsPlayed;
    }

    enum StateOfHand {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }


    public Deal(int id, String userName) {
        AbstractDao<Game> dao2 = new AbstractDao(Game.class);
        UserDao dao = new UserDao();

        currentUser = dao.getUser(userName);
        currentGame = dao2.get(id);
        numberOfPlayers = currentGame.getNumberOfPlayers();
        this.setStartingChips(currentGame.getStartingChips());

        bigBlind = currentGame.getStartingChips() / 20;
        smallBlind = bigBlind / 2;
        handsPlayed=0;
        bet = 0.00;
        logger.debug(bigBlind);
        //currentGame = newGame;
        logger.debug("New Deal");
        stateOfHand = StateOfHand.CLEAR;
        deck = new Deck();
        playersInGame = new ArrayList<>();

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

        newPlayer = new HumanPlayer();
        newPlayer.setPlayerName(currentUser.getUserName());
        newPlayer.setPlayersStartingChips(startingChips);
        playersInGame.add(newPlayer);

        for (int i=1; i< numberOfPlayers; i++) {

            newPlayer = new HumanPlayer();
            newPlayer.setPlayerName("player" + (i + 1));
            playersInGame.add(newPlayer);
        }
        //logger.debug(playersInGame.toString());
        for (HumanPlayer eachPlayer: playersInGame) {
            for (int j=0; j < 2; j++) {
                String cardPosition;
                cardPosition = "Hole Card #" + (j +1);
                eachPlayer.playersHoleCards.put(cardPosition,deck.removeCard());
            }
        }
        //create it's own method
        bet = bigBlind;
        for (int i = 1; i < playersInGame.size();i++)
        for (HumanPlayer player: playersInGame) {
            logger.debug(player.getPlayerName());
            logger.debug(startingChips);
            logger.debug(player.getPlayersStartingChips());
            playersInGame.get(0).setPlayersStartingChips(startingChips);
            //player.setPlayersStartingChips(startingChips);
            Action action = new Action(this, player);
            action.move(bet);
        }

        //create it's own method

    }

    private void flop() {
        for (int i=0; i<3; i++) {
            communityCards.add(deck.removeCard());
        }
        for(HumanPlayer player: playersInGame) {
            Action action = new Action(this, player);
            action.move(bet);
        }
        stateOfHand = StateOfHand.FLOP;
    }

    private void turn() {
        communityCards.add(deck.removeCard());
        for(HumanPlayer player: playersInGame) {
            Action action = new Action(this, player);
            action.move(bet);
        }
        stateOfHand = StateOfHand.TURN;
    }

    private void river() {
        communityCards.add(deck.removeCard());
        for(HumanPlayer player: playersInGame) {
            Action action = new Action(this, player);
            action.move(bet);
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

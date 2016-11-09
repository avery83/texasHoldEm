package poker;
import org.apache.log4j.*;
import java.util.*;

public class Deal {

    private final Logger logger = Logger.getLogger(Deal.class.getName());
    private Deck deck;
    private int numberOfPlayers;
    private List<HumanPlayer> playersInGame;



    private List<Card> communityCards = new ArrayList<>(5);
    private StateOfHand stateOfHand;
    private HumanPlayer newPlayer;


    enum StateOfHand {
        CLEAR,
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }


    public Deal() {

        logger.debug("New Deal");
        stateOfHand = StateOfHand.CLEAR;
        deck = new Deck();
        playersInGame = new ArrayList<>();
        setNumberOfPlayers(4);
    }


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
        dealHands();
        //set state of hand to preflop
        stateOfHand = StateOfHand.PREFLOP;
    }

    private void dealHands() {
        for (int i=0; i<numberOfPlayers; i++) {
            newPlayer = new HumanPlayer();
            newPlayer.setPlayerName("Player: " + (i+1));
            for (int j=0; j < 2; j++) {
                String cardPosition;
                cardPosition = "Hole Card #" + (j +1);
                newPlayer.playersHoleCards.put(cardPosition,deck.removeCard());
            }
            playersInGame.add(newPlayer);//stay here
        }
    }

    private void flop() {
        for (int i=0; i<3; i++) {
            communityCards.add(deck.removeCard());
        }
        stateOfHand = StateOfHand.FLOP;
    }

    private void turn() {
        communityCards.add(deck.removeCard());
        stateOfHand = StateOfHand.TURN;
    }

    private void river() {
        communityCards.add(deck.removeCard());
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

    private List<Rank> scoreList = new ArrayList<>();
    private HashMap<HumanPlayer, Rank> playerToScoreMap = new HashMap<>();
    private HashMap<Rank, HumanPlayer> scoreToPlayerMap = new HashMap<>();
    private HashMap<Rank, Integer> scoreToRankMap = new HashMap<>();


    public void scoreHands() {
        getScoreList().clear();
        Evaluator evaluator = new Evaluator();
        for(HumanPlayer eachPlayer: playersInGame) {
            ArrayList<Card> cards = getCombinedCards(eachPlayer);
            Rank score = evaluator.evaluate(cards);
            getScoreList().add(score);
            playerToScoreMap.put(eachPlayer, score);
            scoreToPlayerMap.put(score, eachPlayer);
        }

        // sort the scores
        Collections.sort(scoreList);

        Rank score;
        for (int i=0; i<getNumberOfPlayers(); i++) {
            int rank = i+1;
            score = scoreList.get(getNumberOfPlayers()-i-1);
            scoreToRankMap.put(score, rank);
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
}

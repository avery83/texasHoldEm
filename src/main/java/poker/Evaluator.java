package poker;
import org.apache.log4j.Logger;
import java.util.*;
import java.util.stream.Collectors;
import poker.Rank.Ranking;
import poker.Card.CardRank;

public class Evaluator {

    static Logger logger = Logger.getLogger(Evaluator.class);
    Rank score;
    private List<Card> remainingCards = new ArrayList<>();
    private List<Card> straightCards = new ArrayList<>();
    private int lastCardPosition;
    private ArrayList<Card> cards;
    private ArrayList<Card> highCards;

    public Rank evaluate(ArrayList<Card> cards) {

        this.cards = cards;
        Collections.sort(this.cards);
        logger.debug("evaluate: " + this.cards.toString());
        score = new Rank();
        highCards = score.getHighCards();
        remainingCards.clear();
        remainingCards.addAll(this.cards);

        //check for flush
        if (!flush()) {
            //If there is no flush check for other winning hands
            searchForCardsOfSameRank();
        }
        //logger.debug("score: " + score);
        //logger.debug("not used: " + remainingCards.toString());
        return score;
    }

    //Look for flush
    private boolean flush() {
        // arrange cards in suits
        ArrayList<List<Card>> suits = new ArrayList<>();
        List<Card> listOfClubs = cards.stream().filter(s -> s.toString().endsWith("C")).collect(Collectors.toList());
        List<Card> listOfDiamonds = cards.stream().filter(s -> s.toString().endsWith("D")).collect(Collectors.toList());
        List<Card> listOfHearts = cards.stream().filter(s -> s.toString().endsWith("H")).collect(Collectors.toList());
        List<Card> listOfSpades = cards.stream().filter(s -> s.toString().endsWith("S")).collect(Collectors.toList());

        suits.add(listOfClubs);
        suits.add(listOfDiamonds);
        suits.add(listOfHearts);
        suits.add(listOfSpades);

        for (List<Card> cardList : suits) {
            lastCardPosition = cardList.size()-1;
            if (cardList.size() >=5) {
                // flush found, look for straight
                Collections.sort(cardList);
                logger.debug("searchBySuit: " + cardList.toString());
                if (searchStraight(cardList)) {
                    // found straight flush
                    score.setRanking(Ranking.STRAIGHT_FLUSH);
                    highCards.addAll(straightCards);
                    remainingCards.removeAll(highCards);
                    //If Last Card In Straight Flush is an Ace then it is an Royal Flush
                    if (highCards.get(0).getCardRank() == CardRank.ACE) {
                        score.setRanking(Ranking.ROYAL_FLUSH);
                        return true;
                    }
                }
                score.setRanking(Ranking.FLUSH);

                // get five cards
                highCards.addAll(cardList);
                remainingCards.removeAll(highCards);
                return true;
            }
        }
        return false;
    }


    private boolean searchStraight(List<Card> sevenCardList) {

        // duplicates it to avoid mutability
        List<Card> cardList = new ArrayList<>(sevenCardList);
        lastCardPosition = cardList.size()-1;
        //Do a try catch
        if (cardList.size() < 5) {
            logger.debug("searchStraight: asked to search straight on " + cardList.toString());
            return false;
        }

        // check to see if there is a ACE
        //This probably doesn't work
        if (cardList.get(lastCardPosition).getCardRank() == CardRank.ACE) {
            // duplicate the ACE at the top
            cardList.add(0, cardList.get(lastCardPosition));
        }

        int numberOfStraightCards=0;
        Card cardToCheckAgainst = null;

        straightCards.clear();
        for (Card cardToCheck: cardList) {
            Card currentCard = cardToCheck;

            if (cardToCheckAgainst == null || currentCard.getCardRank().getCardValue() == cardToCheckAgainst.getCardRank().getCardValue()-1 || currentCard.getCardRank()==CardRank.ACE && cardToCheckAgainst.getCardRank()==CardRank.TWO ) {

                numberOfStraightCards++;
                straightCards.add(currentCard);

                if (numberOfStraightCards==5)
                    return true;
                //current card becomes card to check against
                cardToCheckAgainst = currentCard;

            } else if (currentCard.getCardRank().getCardValue() == cardToCheckAgainst.getCardRank().getCardValue() ) {
                continue;

            } else {
                straightCards.clear();
                straightCards.add(currentCard);
                //current card becomes card to check against
                cardToCheckAgainst = currentCard;
                numberOfStraightCards = 1;
            }
        }

        straightCards.clear();

        return false;
    }

    private void searchForCardsOfSameRank() {

        //lists of piles of cards of the same rank
        ArrayList<ArrayList<Card>> PilesOfSameRank = new ArrayList<>();

        //separate cards into piles
        ArrayList<Card> pileOfSameRank = new ArrayList<>();

        int pileSize = 1;
        int pileLocation = 0;

        boolean Pair = false;
        int pairLocation = 0;

        boolean twoPair = false;
        int twoPairLocation = 0;

        boolean sameRank;

        for (int i = cards.size()-1; i >=0; i--) {
            if (pileOfSameRank.size() == 0 || cards.get(i).getCardRank() == pileOfSameRank.get(0).getCardRank() ) {
                pileOfSameRank.add(cards.get(i));
                sameRank = true;
            } else {
                sameRank = false;
            }
            if (!sameRank || i == 0) {
                PilesOfSameRank.add(pileOfSameRank);
                if (!Pair && pileOfSameRank.size() == 2) {
                    Pair = true;
                    pairLocation = PilesOfSameRank.lastIndexOf(pileOfSameRank);
                } else if (!twoPair && pileOfSameRank.size() == 2) {
                    twoPair = true;
                    twoPairLocation = PilesOfSameRank.lastIndexOf(pileOfSameRank);
                }
                if (pileOfSameRank.size() > pileSize) {
                    pileSize++;
                    pileLocation = PilesOfSameRank.lastIndexOf(pileOfSameRank);
                }
                pileOfSameRank = new ArrayList<>();

                pileOfSameRank.add(cards.get(i));
                //sameRank = true;
            }
        }

        if (pileSize == 4) {
            score.setRanking(Ranking.FOUR_OF_A_KIND);
            highCards.addAll(PilesOfSameRank.get(pileLocation));
            remainingCards.removeAll(PilesOfSameRank.get(pileLocation));
            highCards.add(remainingCards.get(remainingCards.size() -1));
            return;
        }

        if (pileSize == 3 && Pair) {
            score.setRanking(Ranking.FULL_HOUSE);
            highCards.addAll(PilesOfSameRank.get(pileLocation));
            highCards.addAll(PilesOfSameRank.get(pairLocation));
            remainingCards.removeAll(highCards);
            return;
        }

        if (searchStraight(cards)) {
            score.setRanking(Ranking.STRAIGHT);
            highCards.addAll(straightCards);
            remainingCards.removeAll(highCards);
            return;
        }
        if (pileSize == 3) {
            highCards.addAll(PilesOfSameRank.get(pileLocation));

            // three of a kind
            score.setRanking(Ranking.THREE_OF_A_KIND);
            remainingCards.removeAll(highCards);
            highCards.add(remainingCards.remove(remainingCards.size()-1));
            highCards.add(remainingCards.remove(remainingCards.size()-1));
            return;
        }

        if (Pair) {
            highCards.addAll(PilesOfSameRank.get(pairLocation));

            // search for pair
            if (twoPair) {
                score.setRanking(Ranking.TWO_PAIR);
                highCards.addAll(PilesOfSameRank.get(twoPairLocation));
                remainingCards.removeAll(highCards);
                highCards.add(remainingCards.remove(remainingCards.size()-1));//move to variable
                return;
            }

            // pair
            score.setRanking(Ranking.PAIR);
            remainingCards.removeAll(highCards);
            for (int j=0; j<3; j++)
                highCards.add(remainingCards.remove(remainingCards.size()-1));
            return;
        }

        // high Card
        for (int i=0; i<5; i++) {
            highCards.add(remainingCards.remove(remainingCards.size()-1));
        }
    }
}
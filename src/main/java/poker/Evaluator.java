package poker;
import org.apache.log4j.Logger;
import java.util.*;
import java.util.stream.Collectors;
import poker.ScoreEnum.Score;
import poker.CardRankEnum.CardRank;

public class Evaluator {

    static Logger logger = Logger.getLogger(Evaluator.class);
    private Rank score;
    private List<Card> remainingCards;
    private List<Card> straightCards;
    private int lastCardPosition;
    private ArrayList<Card> cards;
    private ArrayList<List<Card>> suits;
    private ArrayList<Card> highCards;


    public Evaluator() {
        remainingCards = new ArrayList<>();
        straightCards = new ArrayList<>();
    }



    public Rank evaluate(ArrayList<Card> cards) {

        this.setCards(cards);
        Collections.sort(getCards());
        score = new Rank();
        highCards = score.getHighCards();
        logger.debug(highCards);
        remainingCards.clear();
        remainingCards.addAll(this.cards);

        //check for flush
        if (!flush()) {
            logger.debug("no flush");
            //If there is no flush check for other winning hands
            searchForCardsOfSameRank();
        }
        //return rank
        return score;
    }

    //Look for flush
    public boolean flush() {

        // arrange cards in suits
        pileCardsBySuit();


        for (List<Card> cardList : suits) {
            lastCardPosition = cardList.size() - 1;
            if (cardList.size() >= 5) {
                // flush found, look for straight
                Collections.sort(cardList);
                logger.debug("searchBySuit: " + cardList.toString());
                if (searchStraight(cardList)) {
                    // found straight flush
                    score.setRanking(Score.STRAIGHT_FLUSH);
                    highCards.addAll(straightCards);
                    remainingCards.removeAll(highCards);
                    //If Last Card In Straight Flush is an Ace then it is an Royal Flush
                    if (highCards.get(0).getCardRank() == CardRank.ACE) {
                        score.setRanking(Score.ROYAL_FLUSH);
                        return true;
                    }
                }
                score.setRanking(Score.FLUSH);

                // get five cards
                highCards.addAll(cardList);
                remainingCards.removeAll(highCards);
                return true;
            }
        }
        return false;
    }

    public void pileCardsBySuit() {
        suits = new ArrayList<>();
        //Get a stream of each suit
        List<Card> listOfClubs = cards.stream().filter(s -> s.toString().endsWith("C")).collect(Collectors.toList());
        List<Card> listOfDiamonds = cards.stream().filter(s -> s.toString().endsWith("D")).collect(Collectors.toList());
        List<Card> listOfHearts = cards.stream().filter(s -> s.toString().endsWith("H")).collect(Collectors.toList());
        List<Card> listOfSpades = cards.stream().filter(s -> s.toString().endsWith("S")).collect(Collectors.toList());

        suits.add(listOfClubs);
        suits.add(listOfDiamonds);
        suits.add(listOfHearts);
        suits.add(listOfSpades);
    }


    public boolean searchStraight(List<Card> cardList) {

        // check for ace
        if (cardList.get(cardList.size() - 1).getCardRank() == CardRank.ACE) {
            // duplicate the ACE at the top
            logger.debug("there is an ace");
            cardList.add(0, cardList.get(6));
        }

        int numberOfStraightCards = 0;
        Card cardToCheckAgainst = null;

        straightCards.clear();
        //logger.debug(cardList);
        for (Card cardToCheck : cardList) {
            //logger.debug(cardToCheck);
            //logger.debug(cardToCheck.getCardRank().getCardValue());
            //logger.debug(cardToCheckAgainst.getCardRank().getCardValue()-1);
            //check to see if the next card is one rank above the previous
            if (cardToCheckAgainst == null || cardToCheck.getCardRank().getCardValue() == cardToCheckAgainst.getCardRank().getCardValue() + 1 || cardToCheck.getCardRank() == CardRank.ACE && cardToCheckAgainst.getCardRank() == CardRank.TWO) {

                numberOfStraightCards++;
                straightCards.add(cardToCheck);
                //logger.debug(straightCards);
                //current card becomes card to check against
                cardToCheckAgainst = cardToCheck;
                //found a straight return true
                if (numberOfStraightCards == 5) {
                    return true;
                }


            } else {
                straightCards.clear();
                straightCards.add(cardToCheck);
                //current card becomes card to check against
                cardToCheckAgainst = cardToCheck;
                numberOfStraightCards = 1;
            }
        }
        //no straight clear cards and return false
        straightCards.clear();
        return false;
    }

    //group cards of same rank
    private void searchForCardsOfSameRank() {
        //stream cards by rank and collect each pile in a map---streams are awesome
        Map<CardRank, List<Card>> sameRanks = cards.stream().collect(Collectors.groupingBy(Card::getCardRank));

        List<List<Card>> listOfPilesOfSameRank = new ArrayList<>();


        for (Map.Entry<CardRank, List<Card>> entry1 : sameRanks.entrySet()) {
            listOfPilesOfSameRank.add(entry1.getValue());
        }
        logger.debug(listOfPilesOfSameRank);
        //sort list of lists by size
        listOfPilesOfSameRank.sort(Comparator.comparing(List::size));
        //revere order
        Collections.reverse(listOfPilesOfSameRank);
        logger.debug(listOfPilesOfSameRank);


            //If first list in list has a size of 4 player has four of a kind
            if (listOfPilesOfSameRank.get(0).size() == 4) {
                score.setRanking(Score.FOUR_OF_A_KIND);
                //Add four of a kind to highCards
                highCards.addAll(listOfPilesOfSameRank.get(0));
                //remove four of a kind from remaining cards
                remainingCards.removeAll(listOfPilesOfSameRank.get(0));
                //add next best card to high cards
                highCards.add(remainingCards.get(remainingCards.size() - 1));
                return;
            }

            //If first list in list has a size of 3 and second list in list has a size of 2 player has a full house
            if (listOfPilesOfSameRank.get(0).size() == 3 && listOfPilesOfSameRank.get(1).size() == 2) {
                score.setRanking(Score.FULL_HOUSE);
                //add three of a kind and pair to highCards
                highCards.addAll(listOfPilesOfSameRank.get(0));
                highCards.addAll(listOfPilesOfSameRank.get(1));
                //remove three of a kind and pair from remainingCards
                remainingCards.removeAll(highCards);
                return;
            }
            //if searchStraight is true player has a straight
            if (searchStraight(cards)) {
                score.setRanking(Score.STRAIGHT);
                //add  5 straight cards to high cards
                highCards.addAll(straightCards);
                //remove straight cards from remainingCards
                remainingCards.removeAll(highCards);
                return;
            }
            //if first list in list has a size of three player has three of a kind
            if (listOfPilesOfSameRank.get(0).size() == 3) {
                score.setRanking(Score.THREE_OF_A_KIND);
                //add three of a kind to highCards
                highCards.addAll(listOfPilesOfSameRank.get(0));
                highCards.add(remainingCards.remove(remainingCards.size() - 1));
                highCards.add(remainingCards.remove(remainingCards.size() - 1));
                //remove three of a kind from remainingCards
                remainingCards.removeAll(highCards);
                return;
            }
            //If first list in list has a size of 2 and second list in list has a size of 2 player has two pair
            if (listOfPilesOfSameRank.get(0).size() == 2 && listOfPilesOfSameRank.get(1).size() == 2) {
                score.setRanking(Score.TWO_PAIR);
                //add both pairs to highCards
                highCards.addAll(listOfPilesOfSameRank.get(0));
                highCards.addAll(listOfPilesOfSameRank.get(1));
                //remove both pairs from remaining cards
                remainingCards.removeAll(highCards);
                //add next best card to highCards
                highCards.add(remainingCards.remove(remainingCards.size() - 1));//move to variable
                return;
            }
            //if first list in list has a size of 2 player has a pair
            if (listOfPilesOfSameRank.get(0).size() == 2) {
                score.setRanking(Score.PAIR);
                //add pair to highCards
                highCards.addAll(listOfPilesOfSameRank.get(0));
                //remove pair from remainingCards
                remainingCards.removeAll(highCards);
                //add three of the next best cards to highCards
                for (int j = 0; j < 3; j++)
                    highCards.add(remainingCards.remove(remainingCards.size() - 1));
                return;
            }


            //put players 5 best cards into highCards
            for (int i = 0; i < 5; i++) {
                highCards.add(remainingCards.remove(remainingCards.size() - 1));
            }

    }
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
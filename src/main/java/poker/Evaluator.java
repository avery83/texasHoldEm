package poker;
import org.apache.log4j.Logger;
import java.util.*;
import java.util.stream.Collectors;

import poker.Deal;
import poker.Rank.Ranking;
import poker.Rank;
import poker.Card.CardRank;
public class Evaluator {

    static Logger logger = Logger.getLogger(Evaluator.class);

    Rank score;

    private List<Card> remainingCards = new ArrayList<>();

    private List<Card> straightCards = new ArrayList<>();
    private int lastCardPosition;

    private ArrayList<Card> cards;
    private int totalCards;
    private ArrayList<Card> highCards;

    public Rank evaluate(Card [] cards) {

        this.cards = new ArrayList<>(Arrays.asList(cards));
        totalCards = cards.length;
        Collections.sort(this.cards);
        logger.debug("evaluate: " + this.cards.toString());

        score = new Rank();

        highCards = score.getHighCards();

        remainingCards.clear();
        remainingCards.addAll(this.cards);

        // count cards in suits, look for flush, straight flush
        if ( ! searchBySuit() ) {

            // flush and four of a kind, full house are mutually exclusive
            // so it is safe to search flush before four of a kind and full house
            searchByRank();
        }

        logger.debug("score: " + score);
        logger.debug("not used: " + remainingCards.toString());

        sanityCheck();

        return score;
    }

    private void sanityCheck() {
        // sanity check
        // high cards + remaining == cards

        if ( highCards.size() !=5 || remainingCards.size() != 2 ) {
            throw new Error("sanity check failed!");
        }

        ArrayList<Card> check = new ArrayList<>(highCards);
        check.addAll(remainingCards);
        Collections.sort(check);

        if ( !check.equals(cards) ) {
            throw new Error("sanity check failed!");
        }
    }

    /**
     * count cards in suits, look for flush, straight in suits (straight flush)
     * @return
     */
    private boolean searchBySuit() {

        // arrange cards in suits



        @SuppressWarnings("unchecked")
        ArrayList<List<Card>> suits = new ArrayList<>();
        List<Card> listOfClubs = cards.stream().filter(s -> s.toString().endsWith("C")).collect(Collectors.toList());
        List<Card> listOfDiamonds = cards.stream().filter(s -> s.toString().endsWith("D")).collect(Collectors.toList());
        List<Card> listOfHearts = cards.stream().filter(s -> s.toString().endsWith("H")).collect(Collectors.toList());
        List<Card> listOfSpades = cards.stream().filter(s -> s.toString().endsWith("S")).collect(Collectors.toList());
        logger.debug("Oh Yeah :)");
        //ArrayList<Card> cardsWithSameSuit;// = new ArrayList<>();
        //ArrayList<Card> [] cardsInSuits = new ArrayList[4];
        suits.add(listOfClubs);
        suits.add(listOfDiamonds);
        suits.add(listOfHearts);
        suits.add(listOfSpades);
        //for (ArrayList<Card> suitsList: suits) {
            //cardsWithSameSuit = new ArrayList<>();
            //cardsInSuits[i] = new ArrayList<>();
            //suitsList.add(listOfClubs);

        //}
        //for (List stuff: suits) {
        //for(ArrayList listAndStuff: suits) {
            //listAndStuff.addAll(cards);
        //}
            //for (Card card : cards) {
                //cardsInSuits[card.getSuit().getCardValue()].add(card);
                //cardsWithSameSuit.add(card);
            //}
        //}

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

                    if (highCards.get(0).getCardRank() == CardRank.ACE)
                        score.setRanking(Ranking.ROYAL_FLUSH);

                    return true;
                }

                score.setRanking(Ranking.FLUSH);

                // fill pattern
                for (int j=0; j<5; j++) {
                    highCards.add(cardList.get(lastCardPosition));
                }
                remainingCards.removeAll(highCards);

                return true;
            }
        }
        return false;
    }

    /**
     * cardRanks is sorted
     * @param a list of cards
     * @return highest ranking, 14 if ace, 0 not found
     */
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
            // duplicate the ACE at the head
            cardList.add(0, cardList.get(lastCardPosition));
        }

        int numberOfStraightCards=0;
        Card cardToCheckAgainst = null;

        straightCards.clear();
        for (Card cardToCheck: cardList) {
        //for (int i = cardList.size()-1; i>=0; i-- ) {

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

    private void searchByRank() {

        // lists of a pile of cards of the same rank
        ArrayList< ArrayList<Card> > CardsOfSameRank = new ArrayList<>();

        // separate cards into piles
        ArrayList<Card> tempList = new ArrayList<>();

        int numberOfCardsWithSameValue = 1;
        int highestSameCardListIdx = 0;

        boolean hasPair = false;
        int pairIndex = 0;

        boolean hasSecondPair = false;
        int secondPairIndex = 0;

        boolean streak;
logger.debug(cards);

        for (int i = cards.size()-1; i >=0; i--) {
    //filter

            //List<Card> streamList = cards.stream().filter(s -> s.toString().endsWith("C")).collect(Collectors.toList());
        //List<Card> streamList = cards.stream().collect(Collectors.groupingBy(C);
            if (tempList.size() == 0 || cards.get(i).getCardRank() == tempList.get(0).getCardRank() ) {
                tempList.add(0, cards.get(i));
                //logger.debug(tempList);
                streak = true;
            } else
                streak = false;
            logger.debug(tempList);
            if (!streak || i == 0) {
                CardsOfSameRank.add(tempList);

                if (!hasPair && tempList.size() == 2) {
                    hasPair=true;
                    logger.debug(CardsOfSameRank);
                    pairIndex = CardsOfSameRank.size() -1;
                } else if ( !hasSecondPair && tempList.size() == 2) {
                    hasSecondPair = true;
                    secondPairIndex = CardsOfSameRank.size() -1;
                }

                if (tempList.size() > numberOfCardsWithSameValue) {
                    numberOfCardsWithSameValue = tempList.size();
                    highestSameCardListIdx = CardsOfSameRank.size() -1;
                }

                tempList=new ArrayList<>();
                //logger.debug(cards.get(i));
                tempList.add(cards.get(i));


                //streak = true;
            }
            logger.debug(CardsOfSameRank);
        }

        if (numberOfCardsWithSameValue == 4) {
            score.setRanking(Ranking.FOUR_OF_A_KIND);
            highCards.addAll(CardsOfSameRank.get(highestSameCardListIdx));
            remainingCards.removeAll(CardsOfSameRank.get(highestSameCardListIdx));
            highCards.add(remainingCards.get(remainingCards.size() -1));
            return;
        }

        if (numberOfCardsWithSameValue == 3 && hasPair) {
            score.setRanking(Ranking.FULL_HOUSE);
            highCards.addAll(CardsOfSameRank.get(highestSameCardListIdx));
            highCards.addAll(CardsOfSameRank.get(pairIndex));
            remainingCards.removeAll(highCards);
            return;
        }

        if (searchStraight(cards)) {
            score.setRanking(Ranking.STRAIGHT);
            highCards.addAll(straightCards);
            remainingCards.removeAll(highCards);
            return;
        }

        if (numberOfCardsWithSameValue==3) {
            highCards.addAll(CardsOfSameRank.get(highestSameCardListIdx));

            // three of a kind
            score.setRanking(Ranking.THREE_OF_A_KIND);
            remainingCards.removeAll(highCards);
            highCards.add(remainingCards.remove(remainingCards.size()-1));
            highCards.add(remainingCards.remove(remainingCards.size()-1));
            return;
        }

        if (hasPair) {
            highCards.addAll(CardsOfSameRank.get(pairIndex));

            // search for pair
            if (hasSecondPair) {
                score.setRanking(Ranking.TWO_PAIR);
                highCards.addAll(CardsOfSameRank.get(secondPairIndex));
                remainingCards.removeAll(highCards);
                highCards.add(remainingCards.remove(remainingCards.size()-1));
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

    public List<Card> getRemainingCards() {
        return remainingCards;
    }

    public List<Card> getStraightCards() {
        return straightCards;
    }

    public Rank getScore() {
        return score;
    }

}
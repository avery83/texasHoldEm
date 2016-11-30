package poker;
import org.apache.log4j.Logger;
import java.util.*;
import java.util.stream.Collectors;
import poker.Rank.Score;
import poker.CardRankEnum.CardRank;

public class Evaluator {

    static Logger logger = Logger.getLogger(Evaluator.class);
    private Rank score;
    private List<Card> remainingCards;
    private List<Card> straightCards;
    private int lastCardPosition;
    private ArrayList<Card> cards;
    private ArrayList<List<Card>> suits;
    public int lastCardIndex = 6;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private ArrayList<Card> highCards;

    public Evaluator() {
        remainingCards = new ArrayList<>();
        straightCards = new ArrayList<>();
    }

    public Rank evaluate(ArrayList<Card> cards) {

        this.setCards(cards);
        Collections.sort(getCards());
        logger.debug("evaluate: " + getCards().toString());
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
        //logger.debug("score: " + score);
        //logger.debug("not used: " + remainingCards.toString());
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

        //List<Card> cardList = new ArrayList<>(sevenCardList);
        //lastCardPosition = sevenCardList.size()-1;
        //Do a try catch
        if (cardList.size() < 5) {
            logger.debug("searchStraight: asked to search straight on " + cardList.toString());
            return false;
        }

        // check to see if there is a ACE
        //This probably doesn't work
        if (cardList.get(cardList.size() - 1).getCardRank() == CardRank.ACE) {
            // duplicate the ACE at the top
            logger.debug("there is an ace");
            cardList.add(0, cardList.get(lastCardIndex));
        }

        int numberOfStraightCards = 0;
        Card cardToCheckAgainst = null;

        straightCards.clear();
        //logger.debug(cardList);
        for (Card cardToCheck : cardList) {
            //logger.debug(cardToCheck);
            //logger.debug(cardToCheck.getCardRank().getCardValue());
            //logger.debug(cardToCheckAgainst.getCardRank().getCardValue()-1);

            if (cardToCheckAgainst == null || cardToCheck.getCardRank().getCardValue() == cardToCheckAgainst.getCardRank().getCardValue() + 1 || cardToCheck.getCardRank() == CardRank.ACE && cardToCheckAgainst.getCardRank() == CardRank.TWO) {

                numberOfStraightCards++;
                straightCards.add(cardToCheck);
                //logger.debug(straightCards);
                //current card becomes card to check against
                cardToCheckAgainst = cardToCheck;
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

        straightCards.clear();

        return false;
    }

    private void searchForCardsOfSameRank() {


        Map<CardRank, List<Card>> sameRanks = cards.stream().collect(Collectors.groupingBy(Card::getCardRank));

        List<List<Card>> glr = new ArrayList<>();


        for (Map.Entry<CardRank, List<Card>> entry1 : sameRanks.entrySet()) {
            glr.add(entry1.getValue());
        }
        logger.debug(glr);
        glr.sort(Comparator.comparing(List::size));
        Collections.reverse(glr);
        logger.debug(glr);



            if (glr.get(0).size() == 4) {
                score.setRanking(Score.FOUR_OF_A_KIND);
                highCards.addAll(glr.get(0));
                remainingCards.removeAll(glr.get(0));
                highCards.add(remainingCards.get(remainingCards.size() - 1));
                return;
            }

            if (glr.get(0).size() == 3 && glr.get(1).size() == 2) {
                //threeOfAKind = true;
                score.setRanking(Score.FULL_HOUSE);
                highCards.addAll(glr.get(0));
                highCards.addAll(glr.get(1));
                remainingCards.removeAll(highCards);
                return;
            }
            if (searchStraight(cards)) {
                score.setRanking(Score.STRAIGHT);
                highCards.addAll(straightCards);
                remainingCards.removeAll(highCards);
                return;
            }

            if (glr.get(0).size() == 3) {
                score.setRanking(Score.THREE_OF_A_KIND);
                highCards.addAll(glr.get(0));
                highCards.add(remainingCards.remove(remainingCards.size() - 1));
                highCards.add(remainingCards.remove(remainingCards.size() - 1));
                remainingCards.removeAll(highCards);
                return;
            }

            if (glr.get(0).size() == 2 && glr.get(1).size() == 2) {
                score.setRanking(Score.TWO_PAIR);
                highCards.addAll(glr.get(0));
                highCards.addAll(glr.get(1));
                remainingCards.removeAll(highCards);
                highCards.add(remainingCards.remove(remainingCards.size() - 1));//move to variable
                return;
            }
            if (glr.get(0).size() == 2) {
                score.setRanking(Score.PAIR);
                highCards.addAll(glr.get(0));
                remainingCards.removeAll(highCards);
                for (int j = 0; j < 3; j++)
                    highCards.add(remainingCards.remove(remainingCards.size() - 1));
                return;
            }


            // high Card
            for (int i = 0; i < 5; i++) {
                highCards.add(remainingCards.remove(remainingCards.size() - 1));
            }

    }
}
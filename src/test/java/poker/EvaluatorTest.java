package poker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import poker.ScoreEnum.Score;

import static org.junit.Assert.*;

/**
 * Created by student on 11/14/16.
 */
public class EvaluatorTest {
    ArrayList<Card> cards2= new ArrayList<>();
    ArrayList<Card> cards3= new ArrayList<>();
    ArrayList<Card> cards4= new ArrayList<>();
    Evaluator eval = new Evaluator();

    @Before
    public void setUp() throws Exception {
        //List<Card> list = new ArrayList<>();

        Card card = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.TWO);
        cards2.add(card);
        Card card2 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.THREE);
        cards2.add(card2);
        Card card3 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.FOUR);
        cards2.add(card3);
        Card card4 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.FIVE);
        cards2.add(card4);
        Card card5 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.SIX);
        cards2.add(card5);
        Card card6 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.SEVEN);
        cards2.add(card6);
        Card card7 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.EIGHT);
        cards2.add(card7);

        Card card8 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.TWO);
        cards3.add(card8);
        Card card9 = new Card(SuitEnum.Suit.HEART, CardRankEnum.CardRank.THREE);
        cards3.add(card9);
        Card card10 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.FOUR);
        cards3.add(card10);
        Card card11 = new Card(SuitEnum.Suit.SPADE, CardRankEnum.CardRank.FIVE);
        cards3.add(card11);
        Card card12 = new Card(SuitEnum.Suit.DIAMOND, CardRankEnum.CardRank.SIX);
        cards3.add(card12);
        Card card13 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.SEVEN);
        cards3.add(card13);
        Card card14 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.EIGHT);
        cards3.add(card14);

        Card card15 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.TWO);
        cards4.add(card15);
        Card card16 = new Card(SuitEnum.Suit.HEART, CardRankEnum.CardRank.THREE);
        cards4.add(card16);
        Card card17 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.THREE);
        cards4.add(card17);
        Card card18 = new Card(SuitEnum.Suit.SPADE, CardRankEnum.CardRank.THREE);
        cards4.add(card18);
        Card card19 = new Card(SuitEnum.Suit.DIAMOND, CardRankEnum.CardRank.FOUR);
        cards4.add(card19);
        Card card20 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.SEVEN);
        cards4.add(card20);
        Card card21 = new Card(SuitEnum.Suit.CLUB, CardRankEnum.CardRank.EIGHT);
        cards4.add(card21);

    }

    @Test
    public void testEvaluate() throws Exception {
        assertEquals(Score.THREE_OF_A_KIND,eval.evaluate(cards4).score);
    }

    @Test
    public void testSearchStraight() {
        assertTrue(eval.searchStraight(cards3));
    }

}
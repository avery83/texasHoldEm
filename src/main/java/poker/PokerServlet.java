package poker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import poker.Deal.*;
import java.util.*;



@WebServlet(
        urlPatterns = {"/pokerServlet"}
)

public class PokerServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(PokerServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //int numberOfPlayers = Integer.parseInt(request.getParameter("numberOfPlayers"));

        Deal dealer = new Deal();

        while (dealer.getStateOfHand() != StateOfHand.RIVER)
            dealer.deal();

        //System.out.println(playersInGame);
        System.out.println(dealer.toString());

        request.setAttribute("myDealers", dealer.toString());
        request.setAttribute("firstCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(0)));
        request.setAttribute("secondCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(1)));
        request.setAttribute("thirdCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(2)));
        request.setAttribute("fourthCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(3)));
        request.setAttribute("fifthCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(4)));

        request.setAttribute("player1FirstHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(0).getPlayersHoleCards().get("Hole Card #1")));
        request.setAttribute("player1SecondHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(0).getPlayersHoleCards().get("Hole Card #2")));
        request.setAttribute("player2FirstHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(1).getPlayersHoleCards().get("Hole Card #1")));
        request.setAttribute("player2SecondHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(1).getPlayersHoleCards().get("Hole Card #2")));
        request.setAttribute("player3FirstHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(2).getPlayersHoleCards().get("Hole Card #1")));
        request.setAttribute("player3SecondHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(2).getPlayersHoleCards().get("Hole Card #2")));
        request.setAttribute("player4FirstHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(3).getPlayersHoleCards().get("Hole Card #1")));
        request.setAttribute("player4SecondHoleCard", dealer.getDeck().getMapOfCardImages().get(dealer.getPlayersInGame().get(3).getPlayersHoleCards().get("Hole Card #2")));
        // evaluate and rank each hand
        dealer.scoreHands();

        List<Rank> scoreList = dealer.getScoreList();
        Comparator cmp = Collections.reverseOrder();
        Collections.sort(scoreList, cmp);
        String winner = "";
        Rank winningScore = new Rank();
        //ArrayList<Card> playerCards = HumanPlayer;
        int i = 0;
        for(Rank score: scoreList) {
            if (i == 0) {
                winner = dealer.getPlayerByScore(score).getPlayerName();
                winningScore = score;
            }
            int rank = dealer.getRankByScore(score);
            System.out.println("rank: " + rank + ", " + (dealer.getPlayerByScore(score).getPlayerName()) +
                    ", score: " + score.toString());
            i++;
        }
        System.out.println("Winner: " + winner);
        request.setAttribute("winner", winner);
        request.setAttribute("winningScore", winningScore);
        //dealer.getCommunityCards();
        logger.debug("Im in the servlet");
        //logger.debug(newString);
        request.setAttribute("myDeal", dealer);
        request.setAttribute("CommunityCards", dealer.getCommunityCards().toString());
        String url = "/index.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
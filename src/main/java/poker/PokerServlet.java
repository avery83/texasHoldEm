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
import poker.Entity.Game;
import poker.persistence.UserDao;
import poker.persistence.AbstractDao;
import java.io.*;

import java.util.*;



@WebServlet(
        urlPatterns = {"/pokerServlet"}
)

public class PokerServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(PokerServlet.class);
    private boolean gameOver;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //int numberOfPlayers = Integer.parseInt(request.getParameter("numberOfPlayers"));
        UserDao dao = new UserDao();
        AbstractDao<Game> dao2 = new AbstractDao(Game.class);
        int id = (Integer) request.getAttribute("gameId");
        String userName = dao2.get(id).getUserName();

        //int newGame;
        //newGame = dao2.get(5).getId();
        //logger.debug();

        //newGame.setUserName(request.getAttribute());
        //newGame.setNumberOfPlayers(4);
        //newGame.setStartingChips(1000.00);
        //String thisName = dao2.get(id).getUserName();
        //dao.getUser(thisName).;

        //if (thisName)
        //if (!gameOver) {
            Deal dealer = new Deal(id, userName );

            while (dealer.getStateOfHand() != StateOfHand.RIVER) {
                dealer.deal();
            }

            //System.out.println(playersInGame);
            System.out.println(dealer.toString());

            request.setAttribute("myDealers", dealer.toString());
            request.setAttribute("firstCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(0)));
            request.setAttribute("secondCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(1)));
            request.setAttribute("thirdCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(2)));
            request.setAttribute("fourthCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(3)));
            request.setAttribute("fifthCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(4)));
        request.setAttribute("startingChips", dealer.getStartingChips());


            for (HumanPlayer player : dealer.getPlayersInGame()) {
                if (player.getPlayerName().equals(userName)) {
                    request.setAttribute("UsersFirstHoleCard",dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #1")));
                    request.setAttribute("UsersSecondHoleCard",dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #2")));
                    request.setAttribute("UsersName", player.getPlayerName());

                } else {
                    request.setAttribute(player.getPlayerName() + "FirstHoleCard", dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #1")));
                    request.setAttribute(player.getPlayerName() + "SecondHoleCard", dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #2")));
                    request.setAttribute(player.getPlayerName(), player.getPlayerName());
                }

            }

            dealer.scoreHands();

            List<Rank> scoreList = dealer.getScoreList();
            Comparator cmp = Collections.reverseOrder();
            Collections.sort(scoreList, cmp);
            String winner = "";
            Rank winningScore = new Rank();
            //ArrayList<Card> playerCards = HumanPlayer;
            int i = 0;
            for (Rank score : scoreList) {
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

            logger.debug("Im in the servlet");
            //logger.debug(newString);
            request.setAttribute("myDeal", dealer);
            request.setAttribute("CommunityCards", dealer.getCommunityCards().toString());
            String url = "/playGame.jsp";

            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    //}
}
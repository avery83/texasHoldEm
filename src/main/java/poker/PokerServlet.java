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
import poker.Entity.Users;
import poker.persistence.UserDao;
import poker.persistence.AbstractDao;
import java.io.*;

import java.util.*;


/**
 *  This is the servlet to play the game
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
@WebServlet(
        urlPatterns = {"/pokerServlet"}
)

public class PokerServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(PokerServlet.class);
    private boolean gameOver;
    private int id;
    String buttonClicked;
    /**
     *  Handles HTTP POST requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        id = (Integer) request.getAttribute("gameId");
        UserDao dao = new UserDao();
        AbstractDao<Game> dao2 = new AbstractDao(Game.class);
        //get games username
        String userName = dao2.get(id).getUserName();
        Deal dealer = new Deal(id, userName );
        //keep dealing until all 5 cards are dealt
        while (dealer.getStateOfHand() != StateOfHand.RIVER) {
                dealer.deal();
        }

            request.setAttribute("myDealers", dealer.toString());
            request.setAttribute("firstCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(0)));
            request.setAttribute("secondCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(1)));
            request.setAttribute("thirdCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(2)));
            request.setAttribute("fourthCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(3)));
            request.setAttribute("fifthCard", dealer.getDeck().getMapOfCardImages().get(dealer.getCommunityCards().get(4)));
            request.setAttribute("startingChips", dealer.getStartingChips());
            request.setAttribute("players", dealer.getPlayersInGame());
            request.setAttribute("numberOfPlayers", dealer.getPlayersInGame().size());


            for (HumanPlayer player : dealer.getPlayersInGame()) {
                if (player.getPlayerName().equals(userName)) {
                    request.setAttribute("UsersFirstHoleCard",dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #1")));
                    request.setAttribute("UsersSecondHoleCard",dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #2")));
                    request.setAttribute("UsersName", player.getPlayerName());

                } else {
                    request.setAttribute(player.getPlayerName() + "FirstHoleCard", dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #1")));
                    request.setAttribute(player.getPlayerName() + "SecondHoleCard", dealer.getDeck().getMapOfCardImages().get(player.getPlayersHoleCards().get("Hole Card #2")));
                    request.setAttribute(player.getPlayerName(), player.getPlayerName());
                    request.setAttribute(player.getPlayerName() + "playersChips", player.getPlayersStartingChips());
                    request.setAttribute(player.getPlayerName() + "playersMove", player.getPlayersActions().get(player.getPlayersActions().size()-1).getMove());
                }
            }
            request.setAttribute("bet", dealer.getBet());
            request.setAttribute("firstRoundOfBets", dealer.totalBets);
            dealer.scoreHands();

            List<Rank> scoreList = dealer.getScoreList();
            Comparator cmp = Collections.reverseOrder();
            Collections.sort(scoreList, cmp);
            String winner = "";
            Rank winningScore = new Rank();
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
            request.setAttribute("winner", winner);
            request.setAttribute("winningScore", winningScore);
            Users user = dao.getUser(userName);

        logger.debug(user);
        logger.debug(user.getGamesPlayed());
        int gamesPlayed = user.getGamesPlayed() + 1;
            user.setGamesPlayed(gamesPlayed);
            dao.updateUser(user);
            if (winner.equals(userName)) {
                int gamesWon = user.getGamesWon() + 1;
                user.setGamesWon(gamesWon);
                dao.updateUser(user);
            }
            request.setAttribute("myDeal", dealer);
            request.setAttribute("CommunityCards", dealer.getCommunityCards().toString());
            String url = "/playGame.jsp";

            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
}
package poker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;
import poker.Entity.*;
import poker.util.DaoFactory;
import poker.persistence.AbstractDao;
import poker.persistence.UserDao;

/**
 * Created by student on 11/29/16.
 */
@WebServlet(name = "CreateGame", urlPatterns = { "/createGame" } )


public class CreateGame extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = new Game();
        game.setNumberOfPlayers(Integer.parseInt(req.getParameter("numberOfPlayers")));
        game.setStartingChips(Double.parseDouble(req.getParameter("startingChips")));


        log.debug("Adding Game: " + game.getId());

        AbstractDao dao = DaoFactory.createDao(Game.class);

        dao.create(game);
        log.debug(game.getId());
        req.setAttribute("gameId", game.getId());
        log.debug("Sending back the game...");

        String url = "/pokerServlet";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}

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
 *  This is the servlet to setup the Game
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
@WebServlet(name = "SetupGame", urlPatterns = { "/setupGame" } )


public class SetupGame extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     *  Handles HTTP POST requests.
     *
     *@param  req                  the HttpServletRequest object
     *@param  resp                  the HttpServletResponse object
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("j_username");
        log.debug(name);

        String name2 = req.getRemoteUser();
        req.setAttribute("name", name2);

        String url = "/setupGame.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}

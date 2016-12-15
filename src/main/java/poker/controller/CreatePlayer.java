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
import poker.persistence.UserDao;
import poker.controller.*;
/**
 *  This is the servlet to create a player
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */

@WebServlet(name = "CreatePlayer", urlPatterns = { "/createPlayer" } )


public class CreatePlayer extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());
    /**
     *  Handles HTTP POST requests.
     *
     *@param  req                   the HttpServletRequest object
     *@param  resp                  the HttpServletResponse object
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Users user = new Users(req.getParameter("userName"), 0, 0, 0.00,
                req.getParameter("password"));
        log.debug("Adding User: " + user);
        UserDao dao = new UserDao();
        dao.addUser(user);
        String name = user.getUserName();
        req.setAttribute("name", name);
        String url = "/setupGame.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
    }


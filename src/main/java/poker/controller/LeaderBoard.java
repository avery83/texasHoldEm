package poker.controller;

import org.apache.log4j.Logger;
import poker.Entity.Users;
import poker.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  This is the servlet to create the leaderboard
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */
@WebServlet(name = "LeaderBoard", urlPatterns = { "/leaderBoard" } )


public class LeaderBoard extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());
    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                  the HttpServletRequest object
     *@param  resp                 the HttpServletResponse object
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        UserDao dao = new UserDao();
        log.debug(dao.getAllUsers());
        List<Users> users = dao.getAllUsers();
        req.setAttribute("users", users.get(0).getUserName());
        req.setAttribute("users", users);
        log.debug(users.get(0).getPassword());
        String url = "/leaderBoard.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}

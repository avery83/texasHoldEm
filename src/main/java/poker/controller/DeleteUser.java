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
 *  This is the servlet to delete a user
 *
 *@author    Jason Avery
 *@since     Nov 18 2016
 */

@WebServlet(name = "DeleteUser", urlPatterns = { "/deleteUser" } )


public class DeleteUser extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());
    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                   the HttpServletRequest object
     *@param  resp                  the HttpServletResponse object
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        UserDao dao = new UserDao();
        String userName = req.getParameter("deleteUser");


        log.debug(userName);
        log.debug(dao.getUser(userName));
        Users thisUser = dao.getUser(userName);
        log.debug(thisUser);
        dao.deleteUser(thisUser);

        String url = "/leaderBoard";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
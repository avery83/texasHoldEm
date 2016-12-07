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
@WebServlet(name = "SetupGame", urlPatterns = { "/setupGame" } )


public class SetupGame extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = (String) req.getAttribute("name");
        log.debug(name);

        String url = "/setupGame.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}

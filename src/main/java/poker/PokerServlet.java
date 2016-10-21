package poker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import poker.*;



@WebServlet(
        urlPatterns = {"/pokerServlet"}
)

public class PokerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Deal newDeal = new Deal();
        request.setAttribute("myDeal", newDeal);
        String url = "/index.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numberOfPlayers = Integer.parseInt(request.getParameter("numberOfPlayers"));
        Deal newDeal = new Deal();
        newDeal.setNumberOfPlayers(numberOfPlayers);
        request.setAttribute("myDeal", newDeal);
        String url = "/index.jsp";

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
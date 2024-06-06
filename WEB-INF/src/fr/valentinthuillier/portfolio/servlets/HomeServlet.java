package fr.valentinthuillier.portfolio.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import fr.valentinthuillier.portfolio.DS;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Detect language and set it in the session
        String lang = req.getLocale().getLanguage();
        req.getSession().setAttribute("lang", lang);
        if(DS.getConnection() == null) {
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/install.jsp");
            rq.forward(req, resp);
            return;
        }
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
        rq.forward(req, resp);
    }
}

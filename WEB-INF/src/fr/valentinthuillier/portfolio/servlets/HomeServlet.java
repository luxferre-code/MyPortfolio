package fr.valentinthuillier.portfolio.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Detect language and set it in the session
        String lang = req.getLocale().getLanguage();
        req.getSession().setAttribute("lang", lang);
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
        rq.forward(req, resp);
    }
}

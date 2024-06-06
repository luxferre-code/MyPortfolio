package fr.valentinthuillier.portfolio.servlets.admin;

import fr.valentinthuillier.portfolio.dao.MessageDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/markAsReplied")
public class MarkMessageAsRepliedServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!"true".equals(request.getSession().getAttribute("connected"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }

        String idString = request.getParameter("id");

        int id = -1;
        try {
            id = Integer.parseInt(idString);
        } catch(NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid type of id");
            return;
        }

        MessageDao messageDao = new MessageDao();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");
        if(!messageDao.markAsReplied(id)) {
            request.setAttribute("error", "An error occurred while marking the message as replied");
        }

        rd.forward(request, response);
    }
}

package fr.valentinthuillier.portfolio.servlets.admin;

import fr.valentinthuillier.portfolio.dao.ProjetDao;
import fr.valentinthuillier.portfolio.dto.Projet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteProject")
public class DeleteProjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ProjetDao projetDao = new ProjetDao();
        RequestDispatcher rq = request.getRequestDispatcher("WEB-INF/views/admin-panel.jsp");
        if(!projetDao.delete(new Projet(id, "", ""))) {
            request.setAttribute("error", "Impossible de supprimer ce projet !");
        }
        rq.forward(request, response);        
    }
}

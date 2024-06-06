package fr.valentinthuillier.portfolio.servlets.admin;

import fr.valentinthuillier.portfolio.dao.CompetenceDao;
import fr.valentinthuillier.portfolio.dto.Competence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateCompetence")
public class UpdateCompetenceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!"true".equals(request.getSession().getAttribute("connected"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Competence competence = new Competence(id, name);

        CompetenceDao competenceDao = new CompetenceDao();
        
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if (!competenceDao.update(competence)) {
            request.setAttribute("error", "Erreur lors de la mise à jour de la compétence");
        }

        rq.forward(request, response);
    }
}
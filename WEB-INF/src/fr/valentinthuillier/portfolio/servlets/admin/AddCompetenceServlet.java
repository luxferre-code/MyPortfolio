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

@WebServlet("/addCompetence")
public class AddCompetenceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Competence competence = new Competence(name);

        CompetenceDao competenceDao = new CompetenceDao();
        
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if (!competenceDao.save(competence)) {
            request.setAttribute("error", "Erreur lors de l'ajout de la compétence");
        }

        rq.forward(request, response);
    }
}

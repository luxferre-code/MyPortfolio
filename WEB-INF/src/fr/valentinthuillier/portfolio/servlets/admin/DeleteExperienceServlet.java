package fr.valentinthuillier.portfolio.servlets.admin;

import fr.valentinthuillier.portfolio.dao.JobsDao;
import fr.valentinthuillier.portfolio.dto.Jobs;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteExperience")
public class DeleteExperienceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!"true".equals(request.getSession().getAttribute("connected"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));

        JobsDao jobsDao = new JobsDao();
        
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if(!jobsDao.delete(new Jobs(id, "", "", "", null, null))) {
            request.setAttribute("error", "Erreur lors de la suppression de l'expérience");
        }

        rq.forward(request, response);
    }

}

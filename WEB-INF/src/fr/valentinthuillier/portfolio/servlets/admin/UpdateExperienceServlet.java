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
import java.time.LocalDate;
import java.sql.Date;

@WebServlet("/updateExperience")
public class UpdateExperienceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String entreprise = request.getParameter("entreprise");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Convert to a Date object
        Date start = Date.valueOf(LocalDate.parse(startDate));
        Date end = Date.valueOf(LocalDate.parse(endDate));

        Jobs job = new Jobs(id, name, description, entreprise, start, end);

        JobsDao jobsDao = new JobsDao();
        
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if (!jobsDao.update(job)) {
            request.setAttribute("error", "Erreur lors de la mise à jour de l'expérience");
        }

        rq.forward(request, response);
    }
}

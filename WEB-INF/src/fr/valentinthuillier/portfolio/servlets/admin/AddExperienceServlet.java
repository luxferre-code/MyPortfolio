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
import java.util.Date;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

@WebServlet("/addExperience")
public class AddExperienceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!"true".equals(request.getSession().getAttribute("connected"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String entreprise = request.getParameter("entreprise");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        CharSequenceTranslator cst = StringEscapeUtils.ESCAPE_HTML4.with(StringEscapeUtils.ESCAPE_XML10);
        name = cst.translate(name);
        description = cst.translate(description);
        entreprise = cst.translate(entreprise);

        // Convert to a Date object
        Date start = java.sql.Date.valueOf(LocalDate.parse(startDate));
        Date end = java.sql.Date.valueOf(LocalDate.parse(endDate));

        Jobs job = new Jobs(-1, name, description, entreprise, start, end);

        JobsDao jobsDao = new JobsDao();
        
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if(!jobsDao.save(job)) {
            request.setAttribute("error", "Erreur lors de l'ajout de l'expérience");
        }

        rq.forward(request, response);
    }

}
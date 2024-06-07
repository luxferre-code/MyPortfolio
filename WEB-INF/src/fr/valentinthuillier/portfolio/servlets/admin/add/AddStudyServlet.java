package fr.valentinthuillier.portfolio.servlets.admin.add;

import java.io.IOException;
import java.sql.Date;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

import fr.valentinthuillier.portfolio.dao.StudyDao;
import fr.valentinthuillier.portfolio.dto.Study;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addStudy")
public class AddStudyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!"true".equals(req.getSession().getAttribute("connected"))) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String lieu = req.getParameter("lieu");
        String dateDebutStr = req.getParameter("date_debut");
        String dateFinStr = req.getParameter("date_fin");

        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if (
            name == null || name.isBlank() ||
            description == null || description.isBlank() ||
            lieu == null || lieu.isBlank() ||
            dateDebutStr == null || dateDebutStr.isBlank() ||
            dateFinStr == null || dateFinStr.isBlank()
        ) {
            req.setAttribute("error", "Merci de remplir tous les champs !");
            rq.forward(req, resp);
        }

        CharSequenceTranslator cst = StringEscapeUtils.ESCAPE_HTML4.with(StringEscapeUtils.ESCAPE_XML11);
        name = cst.translate(name);
        description = cst.translate(description);
        lieu = cst.translate(lieu);
        Date dateDebut = Date.valueOf(dateDebutStr);
        Date dateFin = Date.valueOf(dateFinStr);

        StudyDao dao = new StudyDao();

        if(!dao.save(new Study(name, description, lieu, dateDebut, dateFin))) {
            req.setAttribute("error", "Une erreur est survenue lors de l'ajout de l'étude");
        }

        rq.forward(req, resp);
    }
    
}

package fr.valentinthuillier.portfolio.servlets.admin.delete;

import java.io.IOException;

import fr.valentinthuillier.portfolio.dao.ProjetDao;
import fr.valentinthuillier.portfolio.dao.StudyDao;
import fr.valentinthuillier.portfolio.dto.Study;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteStudy")
public class DeleteStudyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!"true".equals(request.getSession().getAttribute("connected"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        
        StudyDao dao = new StudyDao();
        RequestDispatcher rq = request.getRequestDispatcher("WEB-INF/views/admin-panel.jsp");
        if(!dao.delete(new Study(id, "", "", "", null, null))) {
            request.setAttribute("error", "Impossible de supprimer cet étude !");
        }
        rq.forward(request, response); 
    }

      
}

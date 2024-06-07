package fr.valentinthuillier.portfolio.servlets.admin.add;

import fr.valentinthuillier.portfolio.dao.ProjetDao;
import fr.valentinthuillier.portfolio.dto.Projet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/addProject")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class AddProjectServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "contents/projets";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!"true".equals(request.getSession().getAttribute("connected"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // Get the upload path from the servlet context
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Handle file upload
        Part filePart = request.getPart("image");
        String fileName = name.replace(" ", "-") + ".png";
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        Projet project = new Projet(-1, name, description);

        ProjetDao projetDao = new ProjetDao();
        
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");

        if (!projetDao.save(project)) {
            request.setAttribute("error", "Erreur lors de l'ajout du projet");
        }

        rq.forward(request, response);
    }
}

package fr.valentinthuillier.portfolio.servlets.admin.add;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
@WebServlet("/addCV")
public class AddCVServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!"true".equals(req.getSession().getAttribute("connected"))) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be connected to access this page");
            return;
        }
        Part filePart = req.getPart("cv");
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");
        String uploadPath = getServletContext().getRealPath("") + File.separator + "contents" + File.separator + "cv.pdf";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        filePart.write(uploadPath);
        rq.forward(req, resp);
    }

}

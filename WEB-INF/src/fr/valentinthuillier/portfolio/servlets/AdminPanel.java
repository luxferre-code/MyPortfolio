package fr.valentinthuillier.portfolio.servlets;

import fr.valentinthuillier.portfolio.dao.AdminDao;
import fr.valentinthuillier.portfolio.dto.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

import java.io.IOException;

@WebServlet("/admin")
public class AdminPanel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String connected = (String) session.getAttribute("connected");
        RequestDispatcher rq;
        if(connected == null || !connected.equals("true")) {
            rq = req.getRequestDispatcher("/WEB-INF/views/admin-login.jsp");
        } else {
            session.setAttribute("connected", "false");
            rq = req.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp");
        }
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        CharSequenceTranslator cst = StringEscapeUtils.ESCAPE_HTML4.with(StringEscapeUtils.ESCAPE_XML10);
        mail = cst.translate(mail);
        password = cst.translate(password);
        password = DigestUtils.sha512Hex(password);
        HttpSession session = req.getSession(true);
        if(new AdminDao().exists(mail, password)) {
            session.setAttribute("connected", "true");
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else {
            session.setAttribute("connected", "false");
            req.setAttribute("error", "Identifiants incorrects");
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/admin-login.jsp");
            rq.forward(req, resp);
        }
    }
}

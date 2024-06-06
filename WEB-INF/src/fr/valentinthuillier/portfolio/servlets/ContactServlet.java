package fr.valentinthuillier.portfolio.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

import fr.valentinthuillier.portfolio.dao.IDao;
import fr.valentinthuillier.portfolio.dao.MessageDao;
import fr.valentinthuillier.portfolio.dto.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String mail = req.getParameter("email");
        String message = req.getParameter("message");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        CharSequenceTranslator cst = StringEscapeUtils.ESCAPE_HTML4;

        if(name == null || name.isEmpty() || mail == null || mail.isEmpty() || message == null || message.isEmpty()) {
            Response r = new Response("Veuillez remplir tous les champs", false);
            out.println(r.toJSOString());
            return;
        }

        name = cst.translate(name);
        mail = cst.translate(mail);
        message = cst.translate(message);

        if(!mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Response r = new Response("Adresse mail invalide", false);
            out.println(r.toJSOString());
            return;
        }

        Message m = new Message(-1, name, mail, message);
        IDao<Message> dao = new MessageDao();
        if(dao.save(m)) {
            Response r = new Response("Message envoyé", true);
            out.println(r.toJSOString());
        } else {
            Response r = new Response("Erreur lors de l'envoi du message", false);
            out.println(r.toJSOString());
        }
        
    }

    private class Response {
        private final String message;
        private final boolean success;

        public Response(String message, boolean success) {
            this.message = message;
            this.success = success;
        }

        public String toJSOString() {
            return "{\"message\":\"" + message + "\", \"success\":" + success + "}";
        }
    }
    
}

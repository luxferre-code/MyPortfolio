package fr.valentinthuillier.portfolio.servlets;

import fr.valentinthuillier.portfolio.DS;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/install")
public class InstallServerServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(InstallServerServlet.class.getName());
    public static final String path;
    static {
        String tempPath;
        try {
            tempPath = new File(InstallServerServlet.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParent();
        } catch (URISyntaxException e) {
            tempPath = "";
            log.severe("Failed to get the absolute path: " + e.getMessage());
        }
        System.out.println(tempPath);
        path = tempPath;
    }
    // Find install.sql
    private static final String FILENAME_SQL_INSTALL = path + "/install.sql";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(DS.getConnection() == null) {
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/install.jsp");
            rq.forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        String ip = req.getParameter("ip");
        String port = req.getParameter("port");
        String database = req.getParameter("database");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String adminUsername = req.getParameter("adminUsername");
        String adminPassword = req.getParameter("adminPassword");

        // Hashage du mots de passe en SHA512
        adminPassword = DigestUtils.sha512Hex(adminPassword);

        String url = "jdbc:postgresql://" + ip + ":" + port + "/" + database;

        if(!DS.writeConfigFile(url, login, password, true)) {
            req.setAttribute("error", "Connexion à la base de données échouée");
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/install.jsp");
            rq.forward(req, resp);
            return;
        }

        try {
            // Test the database connection
            Connection connection = DS.getConnection();
            assert connection != null;
            Statement stmt = connection.createStatement();
            for(String line : loadSQLScript(FILENAME_SQL_INSTALL)) {
                stmt.addBatch(line);
            }
            stmt.executeBatch();
            createAdminUser(connection, adminUsername, adminPassword);
            resp.sendRedirect(req.getContextPath() + "/admin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", "Connexion à la base de données échouée");
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/views/install.jsp");
            rq.forward(req, resp);
        }
    }

    private void createAdminUser(Connection connection, String adminUsername, String adminPassword) throws SQLException {
        String sql = "INSERT INTO admin (mail, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, adminUsername);
            ps.setString(2, adminPassword);
            ps.executeUpdate();
        }
    }

    private static String[] loadSQLScript(String filename) {
        List<String> script = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = br.readLine()) != null) {
                sb.append(line);
                if (line.trim().endsWith(";")) {
                    script.add(sb.toString());
                    sb = new StringBuilder();
                }
            }

        } catch(IOException e) {
            log.severe("Cannot load sql script install ! (path: " + filename + ")");
        }
        return script.toArray(new String[0]);
    }

}

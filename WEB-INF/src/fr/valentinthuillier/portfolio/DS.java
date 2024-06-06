package fr.valentinthuillier.portfolio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class DS {

    private static final Logger log = Logger.getLogger(DS.class.getName());

    private static final String CONFIG_FILE_PATH = "/config/myportfolio/config.prop";
    private static DS instance = null;
    private final String nom;
    private final String mdp;
    private final String url;

    /**
     * Constructeur de la classe DS (private vu que singleton)
     */
    private DS() throws Exception {
        Properties p = new Properties();

        /* Check if config file exist */
        File file = new File(CONFIG_FILE_PATH);
        if(!file.exists()) {
            this.initConfigFile(file);
        }
        p.load(new FileInputStream(file));
        Class.forName(p.getProperty("driver"));
        this.url = p.getProperty("url").replace("\\:", ":");
        this.nom = p.getProperty("login");
        this.mdp = p.getProperty("password");
    }

    /**
     * Cette méthode permet d'initialiser le fichier de configuration
     * @param file  (File)  -   Fichier de configuration
     * @see java.io.File
     */
    private void initConfigFile(File file) {
        writeConfigFile("UNKNOW", "UNKNOW", "UNKNOW", false);
    }

    public static boolean writeConfigFile(String url, String username, String password, boolean reload) {
        File file = new File(CONFIG_FILE_PATH);
        try {
            try { file.getParentFile().mkdirs(); } catch(Exception e) {}
            try { file.createNewFile(); } catch(Exception e) {}
            Properties p = new Properties();
            p.setProperty("driver", "org.postgresql.Driver");
            p.setProperty("url", url);
            p.setProperty("login", username);
            p.setProperty("password", password);
            p.store(new FileOutputStream(file), null);
            if(reload) { instance = new DS(); }
        } catch(Exception e) {
            log.severe(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Cette méthode permet de récupérer une connexion à la base de données
     * @return  Connection  -   Connexion à la base de données
     * @see java.sql.Connection
     * @see java.sql.DriverManager
     */
    public static Connection getConnection() {
        try {
            if(instance == null)
                instance = new DS();
            return DriverManager.getConnection(instance.url, instance.nom, instance.mdp);
        } catch(Exception e) {}
        return null;
    }

}

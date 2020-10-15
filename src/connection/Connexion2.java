package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion2 {
    public Connection creeConnexion() {

        Properties accessBdD = new Properties();
        File BdDFile = new File(".properties");
        try {
            FileInputStream source = new FileInputStream(BdDFile);
            accessBdD.loadFromXML(source);
        }  catch (IOException ioe) {
            ioe.printStackTrace();
        }

        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=Europe/Paris",
                accessBdD.getProperty("adresse_ip"), accessBdD.getProperty("port"),
                accessBdD.getProperty("bdd"));
        String login = accessBdD.getProperty("login");
        String pwd = accessBdD.getProperty("password");

        Connection maConnexion = null;

		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;
	}
}

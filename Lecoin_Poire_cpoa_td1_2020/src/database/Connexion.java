package database;
import java.sql.*;
public class Connexion {
	public Connection creeConnexion() {
		String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/lecoin2u_cpoa2020";
		String login = "lecoin2u_appli";
		String pwd = "31603422"; 
		Connection maConnexion = null;
		
		try { 
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
			}
		return maConnexion;
	}
}
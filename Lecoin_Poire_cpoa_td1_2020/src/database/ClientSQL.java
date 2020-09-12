package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import metier.Client;

public class ClientSQL {
	
	public void affichClient() {
		
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("select nom, prenom from client");
			
			while (res.next()) {
				String nom = res.getString("nom");
				String prenom = res.getString("prenom");
			}
			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
			
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	
	}
	
	public void ajoutClient(Client cli) {
		
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("insert into client (nom, prenom) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			requete.setString(1, cli.getNom());
			requete.setString(2, cli.getPrenom());
			ResultSet res = requete.executeQuery();
			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
			
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
public void supprClient(Client cli) {
		
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from client where nom=? and prenom=?)", Statement.RETURN_GENERATED_KEYS);
			
			requete.setString(1, cli.getNom());
			requete.setString(2, cli.getPrenom());
			ResultSet res = requete.executeQuery();
			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
			
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}

}

package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Client;

public class ClientSQL {

	public ArrayList<Client> listClient() {

		ArrayList<Client> listeClient = new ArrayList<>();
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("select nom, prenom from client");

			while (res.next()) {
				listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), null, null, null, null, null, null));
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

		return listeClient;

	}

	public void ajoutClient(Client cli) {

		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("insert into client (nom, prenom) values (?, ?)", Statement.RETURN_GENERATED_KEYS);

			requete.setInt(1, cli.getIdClient());
			requete.setString(2, cli.getNom());
			requete.setString(3, cli.getPrenom());
			requete.setString(4, cli.getIdentifiant());
			requete.setString(5, cli.getMdp());
			requete.setString(6, cli.getNum());
			requete.setString(7, cli.getVoie());
			requete.setString(8, cli.getVille());
			requete.setString(9, cli.getPays());
			requete.executeUpdate();
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

			requete.setInt(1, cli.getIdClient());
			requete.setString(2, cli.getNom());
			requete.setString(3, cli.getPrenom());
			requete.setString(4, cli.getIdentifiant());
			requete.setString(5, cli.getMdp());
			requete.setString(6, cli.getNum());
			requete.setString(7, cli.getVoie());
			requete.setString(8, cli.getVille());
			requete.setString(9, cli.getPays());
			requete.executeUpdate();
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

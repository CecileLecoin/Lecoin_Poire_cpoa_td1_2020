package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Client;

public class ClientSQL {

	public static ArrayList<Client> listClient() {

		ArrayList<Client> listeClient = new ArrayList<>();
		
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("select * from Client");

			while (res.next()) {
				listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), null, null, null, null, null, null, null)); //null car pour le td1 on n'a pas besoin de g�rer les autres �lements �  part nom et prenom
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

	public static Client getById(int id) {

		Client client = new Client();
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE id_client=?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();

			while (res.next()) {
				client = new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
						res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"), res.getString("adr_pays")); //null car pour le td1 on n'a pas besoin de g�rer les autres �lements �  part nom et prenom
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
		return client;

	}

	public static void ajoutClient(Client cli) {

		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("insert into Client (id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			requete.setInt(1, cli.getIdClient());
			requete.setString(2, cli.getNom());
			requete.setString(3, cli.getPrenom());
			requete.setString(4, cli.getIdentifiant());
			requete.setString(5, cli.getMdp());
			requete.setString(6, cli.getNum());
			requete.setString(7, cli.getVoie());
			requete.setString(8, cli.getCp());
			requete.setString(9, cli.getVille());
			requete.setString(10, cli.getPays());
			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();
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

	public static void supprClient(Client cli) {

		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client=?", Statement.RETURN_GENERATED_KEYS);

			requete.setInt(1, cli.getIdClient());
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}

	public static void modifClient(Client cli, String nouvNom, String nouvPrenom) {

		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"UPDATE Client SET nom = ?, prenom = ? WHERE id_client = ?");
			requete.setString(1, nouvNom);
			requete.setString(2, nouvPrenom);
			requete.setInt(3, cli.getIdClient());

			/*requete.setString(4, cli.getIdentifiant());
			requete.setString(5, cli.getMdp());
			requete.setString(6, cli.getNum());
			requete.setString(7, cli.getVoie());
			requete.setString(7, cli.getVoie());
			requete.setString(8, cli.getCp());
			requete.setString(9, cli.getVille());
			requete.setString(10, cli.getPays());*/
			requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	private ClientSQL() {
		throw new IllegalStateException("Utility class");
	}

}

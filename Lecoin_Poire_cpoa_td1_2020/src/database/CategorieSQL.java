package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Categorie;

public class CategorieSQL {

	public static ArrayList<Categorie> listCategorie() {

		ArrayList<Categorie> listCategorie = new ArrayList<>();
		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();
			Statement requete = connection.createStatement();
			ResultSet res = requete.executeQuery("select * from Categorie");

			while (res.next()) {
				listCategorie.add(new Categorie(res.getInt("id_categorie"), res.getString("titre"), res.getString("visuel")));
			}
			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
		}

		return listCategorie;
	}

	public static Categorie getById(int id) {

		Categorie categorie = new Categorie();

		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie WHERE id_categorie=?");
			requete.setInt(1, id);

			ResultSet res = requete.executeQuery();

			while(res.next()) {
				categorie = new Categorie(res.getInt("id_categorie"), res.getString("titre"), res.getString("visuel"));
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
		return categorie;
	}

	public static int ajoutCategorie(Categorie categorie) {

		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("INSERT INTO Categorie (titre, visuel) Values (?, ?)",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			int reqBool = requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

			return reqBool;

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());

			return -1;
		}

	}

	public static int supprCategorie(Categorie categorie) {

		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("DELETE FROM Categorie WHERE titre = ? AND visuel = ?",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			int reqBool = requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

			return reqBool;

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
			return -1;
		}
	}

	public static int modifCategorie(Categorie categorie) {

		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie = ?",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			requete.setInt(3, categorie.getIdCategorie());
			int reqBool = requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

			return reqBool;

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
			return -1;
		}
	}

	private CategorieSQL() {
		throw new IllegalStateException("Utility class");
	}
}

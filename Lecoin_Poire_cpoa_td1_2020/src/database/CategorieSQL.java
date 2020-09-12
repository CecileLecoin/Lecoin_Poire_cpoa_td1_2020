package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Categorie;

public class CategorieSQL {

	public ArrayList<Categorie> listCategorie() {

		ArrayList<Categorie> listCategorie = new ArrayList<>();
		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();
			Statement requete = connection.createStatement();
			ResultSet res = requete.executeQuery("select * from client");

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

	public void ajoutCategorie(Categorie categorie) {

		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("INSERT INTO categorie (titre, visuel) Values (?, ?)",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
		}

	}

	public void supprCategorie(Categorie categorie) {

		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("DELETE FROM categorie WHERE titre = ? AND visuel = ?",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
		}
	}

	public void modifCategorie(Categorie categorie) {

		Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("UPDATE categorie SET titre = ?, visuel = ? WHERE id_categorie = ?",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			requete.setInt(3, categorie.getIdCategorie());
			requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
		}
	}
}

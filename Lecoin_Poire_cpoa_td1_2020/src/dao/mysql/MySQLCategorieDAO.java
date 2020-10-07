package dao.mysql;

import dao.CategorieDAO;
import metier.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Connexion;

public class MySQLCategorieDAO implements CategorieDAO {

	private static CategorieDAO instance;

	public static CategorieDAO getInstance() {
		if (instance == null) {
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}

	private MySQLCategorieDAO() {}

    @Override
    public Categorie getById(int id) {
		Categorie categorie = null;

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


    @Override
    public boolean create(Categorie categorie) {
        Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("INSERT INTO Categorie (titre, visuel) Values (?, ?)",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());

			requete.executeUpdate();

			ResultSet res = requete.getGeneratedKeys();
			res.last();
			categorie.setIdCategorie(res.getInt(1));

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

			return true;

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());

			return false;
		}

    }


    @Override
    public boolean update(Categorie categorie) {
        Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie = ?",
					Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			requete.setInt(3, categorie.getIdCategorie());

			requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

			return true;

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
			return false;
		}
    }


    @Override
    public boolean delete(Categorie categorie) {
        Connexion connexion = new Connexion();
		try {
			Connection connection = connexion.creeConnexion();

			PreparedStatement requete = connection.prepareStatement("DELETE FROM Categorie WHERE titre = ? AND visuel = ?",
				Statement.RETURN_GENERATED_KEYS);

			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
			requete.executeUpdate();

			if (requete != null)
				requete.close();
			if (connection != null)
				connection.close();

			return true;

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
			return false;
		}
    }


	@Override
    public ArrayList<Categorie> getByTitre(String titre){

		if (titre == null || titre.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}

        ArrayList<Categorie> listCategorie = new ArrayList<>();
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie WHERE titre=?");
			requete.setString(1, titre);

			ResultSet res = requete.executeQuery();

			while (res.next()) {
				listCategorie.add(new Categorie(res.getInt("id_categorie"), res.getString("titre"), res.getString("visuel")));
			}
			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
		}

		return listCategorie;
    }


	@Override
    public ArrayList<Categorie> getByVisuel(String visuel){

		if (visuel == null || visuel.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}

        ArrayList<Categorie> listCategorie = new ArrayList<>();
		Connexion connexion = new Connexion();
		try {
			Connection laConnexion = connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie WHERE visuel=?");
			requete.setString(1, visuel);

			ResultSet res = requete.executeQuery();

			while (res.next()) {
				listCategorie.add(new Categorie(res.getInt("id_categorie"), res.getString("titre"), res.getString("visuel")));
			}
			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
		}

		return listCategorie;
    }

	@Override
    public ArrayList<Categorie> findAll() {
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

}

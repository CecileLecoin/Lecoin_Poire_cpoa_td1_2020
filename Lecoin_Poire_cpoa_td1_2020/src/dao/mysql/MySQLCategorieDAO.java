package dao.mysql;

import dao.CategorieDAO;
import metier.Categorie;
import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCategorieDAO implements CategorieDAO {


    @Override
    public Categorie getById(int id) {
        return CategorieSQL.getById(id);
    }


    @Override
    public boolean create(Categorie categorie) {
        return CategorieSQL.ajoutCategorie(categorie)==1;
    }


    @Override
    public boolean update(Categorie categorie) {
        return CategorieSQL.modifCategorie(categorie)==1;
    }


    @Override
    public boolean delete(Categorie categorie) {
        return CategorieSQL.supprCategorie(categorie)==1;   // Tout ce qui est au dessus ne fait qu'une ligne pcq on avait anticipé et déjà créer des classes basiques avec des requetes SQL principales
    }


	@Override
    public ArrayList<Categorie> getByTitre(String titre){

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
        return CategorieSQL.listCategorie();
    }

}

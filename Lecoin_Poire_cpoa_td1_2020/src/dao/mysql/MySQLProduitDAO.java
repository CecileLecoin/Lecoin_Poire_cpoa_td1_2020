package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProduitDAO;
import database.Connexion;
import database.ProduitSQL;
import metier.Produit;

public class MySQLProduitDAO implements ProduitDAO {

    @Override
    public Produit getById(int id) {
        return ProduitSQL.getById(id);
    }

    @Override
    public boolean create(Produit produit) {
        return ProduitSQL.ajoutProduit(produit) == 1;
    }

    @Override
    public boolean update(Produit produit) {
        return ProduitSQL.modifProduit(produit) == 1;
    }

    @Override
    public boolean delete(Produit produit) {
        return ProduitSQL.supprProduit(produit) == 1;
    }

    @Override
    public ArrayList<Produit> getByNom(String nom) {

        ArrayList<Produit> listProduit = new ArrayList<>();
        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE nom=?");
            requete.setString(1, nom);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listProduit
                        .add(new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"),
                        res.getString("visuel"), res.getFloat("tarif"), res.getInt("id_categorie")));
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Probleme de selection des donnees" + sqle.getMessage());
        }

        return listProduit;
    }

    @Override
    public ArrayList<Produit> getByDescription(String description) {

        ArrayList<Produit> listProduit = new ArrayList<>();
        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE description=?");
            requete.setString(1, description);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listProduit
                        .add(new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"),
                                res.getString("visuel"), res.getFloat("tarif"), res.getInt("id_categorie")));
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Probleme de selection des donnees" + sqle.getMessage());
        }

        return listProduit;
    }

    @Override
    public ArrayList<Produit> getByTarif(float tarif) {

        ArrayList<Produit> listProduit = new ArrayList<>();
        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE tarif=?");
            requete.setFloat(1, tarif);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listProduit
                        .add(new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"),
                        res.getString("visuel"), res.getFloat("tarif"), res.getInt("id_categorie")));
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Probleme de selection des donnees" + sqle.getMessage());
        }

        return listProduit;
    }

    @Override
    public ArrayList<Produit> getByVisuel(String visuel) {

        ArrayList<Produit> listProduit = new ArrayList<>();
        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE visuel=?");
            requete.setString(1, visuel);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listProduit
                        .add(new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"),
                        res.getString("visuel"), res.getFloat("tarif"), res.getInt("id_categorie")));
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Probleme de selection des donnees" + sqle.getMessage());
        }

        return listProduit;
    }

    @Override
    public ArrayList<Produit> findAll() {
        return ProduitSQL.listProduit();
    }

}

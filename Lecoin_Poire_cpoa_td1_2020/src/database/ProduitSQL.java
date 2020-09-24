package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Produit;

public class ProduitSQL {

    public static ArrayList<Produit> listProduit() {

        ArrayList<Produit> listProduit = new ArrayList<>();
        Connexion connexion = new Connexion();
        try {
            Connection connection = connexion.creeConnexion();
            Statement requete = connection.createStatement();
            ResultSet res = requete.executeQuery("select * from Produit");

            while (res.next()) {
                listProduit.add(new Produit(res.getInt("id_produit"), res.getString("nom"),
                res.getString("description"), res.getString("visuel"), res.getFloat("tarif"), res.getInt("id_categorie")));
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (connection != null)
                connection.close();

            } catch (SQLException sqle) {
                System.out.println("Probleme de selection des donnees" + sqle.getMessage());
            }

        return listProduit;
    }

    public static Produit getById(int id) {

        Produit produit = new Produit();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE id_produit=?");
            requete.setInt(1, id);
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                produit = new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"), res.getString("visuel"), res.getFloat("tarif"), res.getInt("id_categorie"));
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

        return produit;
    }

    public static int ajoutProduit(Produit produit) {

        Connexion connexion = new Connexion();
        try {
            Connection connection = connexion.creeConnexion();

            PreparedStatement requete = connection.prepareStatement(
                "INSERT INTO Produit (id_categorie, nom, description, tarif, visuel, id_categorie) Values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            requete.setInt(1, produit.getIdProduit());
            requete.setString(2, produit.getNom());
            requete.setString(3, produit.getDescription());
            requete.setFloat(4, produit.getTarif());
            requete.setString(5, produit.getVisuel());
            requete.setInt(6, produit.getIdCategorie());
            int reqBool = requete.executeUpdate();

        if (requete != null)
            requete.close();
        if (connection != null)
            connection.close();
        return reqBool;

        } catch (SQLException sqle) {
            System.out.println("Probleme lors de la connexion ou execution de la requete" + sqle.getMessage());
        }

        return -1;
    }

    public static int supprProduit(Produit produit) {

        Connexion connexion = new Connexion();
        try {
            Connection connection = connexion.creeConnexion();

            PreparedStatement requete = connection.prepareStatement(
                    "DELETE FROM Produit WHERE id_produit = ?", Statement.RETURN_GENERATED_KEYS);

            requete.setInt(1, produit.getIdProduit());
            int reqBool = requete.executeUpdate();

            if (requete != null)
                requete.close();
            if (connection != null)
                connection.close();
            return reqBool;

        } catch (SQLException sqle) {
            System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
        }
        return -1;
    }

    public static int modifProduit(Produit produit) {

        Connexion connexion = new Connexion();
        try {
            Connection connection = connexion.creeConnexion();

            PreparedStatement requete = connection.prepareStatement(
                    "UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ?, id_categorie = ? WHERE id_produit = ?",
                    Statement.RETURN_GENERATED_KEYS);

            requete.setString(1, produit.getNom());
            requete.setString(2, produit.getDescription());
            requete.setFloat(3, produit.getTarif());
            requete.setString(4, produit.getVisuel());
            requete.setInt(5, produit.getIdCategorie());
            requete.setInt(6, produit.getIdProduit());
            int reqBool = requete.executeUpdate();

            if (requete != null)
                requete.close();
            if (connection != null)
                connection.close();
            return reqBool;

        } catch (SQLException sqle) {
            System.out.println("Probleme lors de la connexion ou execution de la requete" + sqle.getMessage());
        }
        return -1;
    }

    private ProduitSQL() {
        throw new IllegalStateException("Utility class");
    }
}
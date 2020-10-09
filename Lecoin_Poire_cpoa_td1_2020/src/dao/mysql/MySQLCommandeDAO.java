package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import connection.Connexion;
import dao.ClientDAO;
import dao.CommandeDAO;
import metier.Client;
import metier.Commande;
import metier.Produit;

public class MySQLCommandeDAO implements CommandeDAO {

	 private static CommandeDAO instance;

	    public static CommandeDAO getInstance() {
	        if (instance == null) {
	            instance = new MySQLCommandeDAO();
	        }
	        return instance;
	    }

	    private MySQLCommandeDAO() {}

	@Override
	public Commande getById(int id) {

		Commande commande = null;

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande WHERE id_commande=?");
            requete.setInt(1, id);
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                PreparedStatement requete2 = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=?");
                requete2.setInt(1, id);
                ResultSet res2 = requete2.executeQuery();

                ClientDAO clientdao = MySQLClientDAO.getInstance();
                HashMap<Produit, Integer> produits = new HashMap<>();
                while(res2.next()){

                    Produit produit = MySQLProduitDAO.getInstance().getById(res2.getInt("id_produit"));
                    produits.put(produit, res2.getInt("quantite"));

                }

                commande = new Commande(res.getInt("id_commande"), res.getDate("date_commande").toLocalDate(), clientdao.getById(res.getInt("id_client")), produits);

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

        return commande;
	}

	@Override
	public boolean create(Commande commande) {
		Connexion connexion = new Connexion();
        try {
            Connection connection = connexion.creeConnexion();

            PreparedStatement requete1 = connection.prepareStatement(
                "INSERT INTO Commande (date_commande, id_client) Values (?, ?)", Statement.RETURN_GENERATED_KEYS);


            requete1.setDate(1, java.sql.Date.valueOf(commande.getDate()));
            requete1.setInt(2, commande.getClient().getIdClient());
            requete1.executeUpdate();

            ResultSet res = requete1.getGeneratedKeys();
            res.last();
            commande.setIdCommande(res.getInt(1));

            int index=0; //index nous permets de parcourir une liste de chaque produit de la hashmap
            ArrayList<Produit> listeP = new ArrayList<Produit>();
            listeP.addAll((Collection<? extends Produit>) commande.getProduits().keySet());

            while(listeP.get(index)!=null) {
                PreparedStatement requete2 = connection.prepareStatement(
                	"INSERT INTO Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) Values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                requete2.setInt(1, commande.getIdCommande());
                requete2.setInt(2, listeP.get(index).getIdProduit()); //listeP.get(index) donne le produit
                requete2.setInt(3, commande.getProduits().get(listeP.get(index))); //on recupere la quantite qui correspond au produit donné
                requete2.setFloat(4, listeP.get(index).getTarif());

                requete2.executeUpdate();

            if (requete2 != null)
                requete2.close();
            }

        if (requete1 != null)
            requete1.close();
        if (connection != null)
            connection.close();
        return true;

        } catch (SQLException sqle) {
            System.out.println("Probleme lors de la connexion ou execution de la requete" + sqle.getMessage());
        }

        return false;
	}

	@Override
	public boolean update(Commande commande) {

		Connexion connexion = new Connexion();
        try {
            Connection connection = connexion.creeConnexion();

            PreparedStatement requete = connection.prepareStatement(
                    "UPDATE Commande SET date_commande = ?, id_client = ? WHERE id_commande = ?",
                    Statement.RETURN_GENERATED_KEYS);

            requete.setDate(1, java.sql.Date.valueOf(commande.getDate()));
            requete.setInt(2, commande.getClient().getIdClient());
            requete.setInt(3, commande.getIdCommande());
            requete.executeUpdate();

            int index=0;
            ArrayList<Produit> listeP = new ArrayList<Produit>();
            listeP.addAll((Collection<? extends Produit>) commande.getProduits().keySet());

            while(listeP.get(index)!=null) {
                PreparedStatement requete2 = connection.prepareStatement(
                    "UPDATE Ligne_commande SET id_produit = ?, quantite = ?, tarif_unitaire=? WHERE id_commande = ?",
                    Statement.RETURN_GENERATED_KEYS);

                requete2.setInt(1, listeP.get(index).getIdProduit());
                requete2.setInt(2, commande.getProduits().get(listeP.get(index)));
                requete2.setFloat(3, listeP.get(index).getTarif());
                requete2.setInt(4, commande.getIdCommande());
                requete2.executeUpdate();
            }

            if (requete != null)
                requete.close();
            if (connection != null)
                connection.close();
            return true;

        } catch (SQLException sqle) {
            System.out.println("Probleme lors de la connexion ou execution de la requete" + sqle.getMessage());
        }
        return false;
	}

	@Override
	public boolean delete(Commande commande) {

		Connexion connexion = new Connexion();
         try {
             Connection connection = connexion.creeConnexion();

             PreparedStatement requete = connection.prepareStatement(
                     "DELETE FROM Commande WHERE id_commande= ?", Statement.RETURN_GENERATED_KEYS);

             requete.setInt(1, commande.getIdCommande());
             requete.executeUpdate();

             PreparedStatement requete2 = connection.prepareStatement(
                     "DELETE FROM Ligne_commande WHERE id_commande= ?", Statement.RETURN_GENERATED_KEYS);

             requete2.setInt(1, commande.getIdCommande());
             requete2.executeUpdate();

             if (requete != null)
                 requete.close();
             if (connection != null)
                 connection.close();
             return true;

         } catch (SQLException sqle) {
             System.out.println("Problème lors de la connexion ou execution de la requete" + sqle.getMessage());
         }
         return false;
	}

	@Override
	public ArrayList<Commande> findAll() {

		ArrayList<Commande> lesCommandes = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande");
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                PreparedStatement requete2 = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=?");
                requete2.setInt(1, res.getInt("id_commande"));
                ResultSet res2 = requete2.executeQuery();

                ClientDAO clientdao = MySQLClientDAO.getInstance();
                HashMap<Produit, Integer> produits = new HashMap<>();

                while(res2.next()){

                    Produit produit = MySQLProduitDAO.getInstance().getById(res2.getInt("id_produit"));
                    produits.put(produit, res2.getInt("quantite"));
                }

                lesCommandes.add(new Commande(res.getInt("id_commande"), res.getDate("date_commande").toLocalDate(), clientdao.getById(res.getInt("id_client")), produits));

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

        return lesCommandes;
	}

	@Override
	public ArrayList<Commande> getByProduit(Produit produit) {

        ArrayList<Commande> lesCommandes = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_produit=?");
            requete.setInt(1, produit.getIdProduit());
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                PreparedStatement requete2 = laConnexion.prepareStatement("SELECT * FROM Commande WHERE id_commande=?");
                requete2.setInt(1, res.getInt("id_commande"));
                ResultSet res2 = requete2.executeQuery();

                ClientDAO clientdao = MySQLClientDAO.getInstance();
                HashMap<Produit, Integer> produits = new HashMap<>();
                while(res2.next()){

                    produit.setIdProduit(produit.getIdProduit());
                    produits.put(produit, res2.getInt("quantite"));


                }

                lesCommandes.add(new Commande(res.getInt("id_commande"), res.getDate("date_commande").toLocalDate(), clientdao.getById(res.getInt("id_client")), produits));

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

        return lesCommandes;
	}

	@Override
	public ArrayList<Commande> getByClient(Client client) {

        ArrayList<Commande> lesCommandes = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande WHERE id_client=?");
            requete.setInt(1, client.getIdClient());
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                PreparedStatement requete2 = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=?");
                requete2.setInt(1, res.getInt("id_commande"));
                ResultSet res2 = requete2.executeQuery();

                HashMap<Produit, Integer> produits = new HashMap<>();
                while(res2.next()){

                    Produit produit = MySQLProduitDAO.getInstance().getById(res2.getInt("id_produit"));
                    produits.put(produit, res2.getInt("quantite"));


                }

                lesCommandes.add(new Commande(res.getInt("id_commande"), res.getDate("date_commande").toLocalDate(), client, produits));

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

        return lesCommandes;
	}

	@Override
	public ArrayList<Commande> getByDate(LocalDate date) {

		ArrayList<Commande> lesCommandes = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande WHERE date_commande=?");
            requete.setDate(1, java.sql.Date.valueOf(date));
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                PreparedStatement requete2 = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=?");
                requete2.setInt(1, res.getInt("id_commande"));
                ResultSet res2 = requete2.executeQuery();

                ClientDAO clientdao = MySQLClientDAO.getInstance();
                HashMap<Produit, Integer> produits = new HashMap<>();
                while(res2.next()){

                    Produit produit = MySQLProduitDAO.getInstance().getById(res2.getInt("id_produit"));
                    produits.put(produit, res2.getInt("quantite"));


                }

                lesCommandes.add(new Commande(res.getInt("id_commande"), res.getDate("date_commande").toLocalDate(), clientdao.getById(res.getInt("id_client")), produits));

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

        return lesCommandes;
	}

}

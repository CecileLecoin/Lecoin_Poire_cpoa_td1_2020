package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import connection.Connexion;
import dao.CategorieDAO;
import dao.CommandeDAO;
import dao.ProduitDAO;
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
		
		Commande commande = new Commande();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande WHERE id_commande=?");
            requete.setInt(1, id);
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                ClientDAO clientdao = MySQLClientDAO.getInstance();
                commande = new Commande(res.getInt("id_commande"), res.getDate("date_commande"), clientdao.getById(res.getInt("id_client")));
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
                "INSERT INTO Commande (id_commande, date_commande, id_client) Values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            

            requete1.setInt(1, commande.getIdCommande());
            requete1.setDate(2, (java.sql.Date) commande.getDate());
            requete1.setInt(3, commande.getClient().getIdClient());
            requete1.executeUpdate();
            
            int index=0;
            ArrayList<Produit> listeP = new ArrayList();
            listeP.addAll((Collection<? extends Produit>) commande.getProduits().keySet());
            
            while(listeP.get(index)!=null) {
            PreparedStatement requete2 = connection.prepareStatement(
                	"INSERT INTO Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) Values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            requete2.setInt(1, commande.getIdCommande());
            requete2.setInt(2, listeP.get(index).getIdProduit());
            requete2.setInt(3, commande.getProduits().get(listeP.get(index)));
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
	public boolean update(Commande object) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public boolean delete(Commande object) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public ArrayList<Commande> findAll() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public ArrayList<Commande> getByProduit(Produit produit) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public ArrayList<Commande> getByClient(Client client) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public ArrayList<Commande> getByDate(Date date) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}

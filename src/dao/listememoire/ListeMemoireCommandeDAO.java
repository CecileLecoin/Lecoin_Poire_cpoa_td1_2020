package dao.listememoire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.CommandeDAO;
import dao.ProduitDAO;
import exceptions.CommandeApplicationException;
import metier.Categorie;
import metier.Client;
import metier.Commande;
import metier.Produit;

public class ListeMemoireCommandeDAO implements CommandeDAO {

	private static ListeMemoireCommandeDAO instance;

	private List<Commande> commandes;

	public static ListeMemoireCommandeDAO getInstance() throws CommandeApplicationException {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireCommandeDAO() throws CommandeApplicationException {

		this.commandes = new ArrayList<Commande>();
	}

	@Override
	public Commande getById(int id) {

		Commande commande = new Commande();
		commande.setIdCommande(id);
		System.out.println("commande id :" + commande.getIdCommande());
		int idx = this.commandes.indexOf(commande);
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune Commande ne possède cet identifiant");
		} else {
			System.out.println("trouvé : " + idx);
			return this.commandes.get(idx);
		}
	}

	@Override
	public boolean create(Commande commande) {

		commande.getIdCommande();
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.commandes.contains(commande)) {

			commande.setIdCommande(commande.getIdCommande() + 1);
		}
		return this.commandes.add(commande);
	}

	@Override
	public boolean update(Commande commande) {

		// Ne fonctionne que si l'objet métier est bien fait...
		try {
			this.commandes.set(commande.getIdCommande(), commande);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Commande commande) {

		Commande supprime;

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.commandes.indexOf(commande);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.commandes.remove(idx);
		}

		return commande.equals(supprime);
	}

	@Override
	public ArrayList<Commande> findAll() {
		System.out.println("findall");

		return (ArrayList<Commande>) this.commandes;
	}

	@Override
	public ArrayList<Commande> getByProduit(Produit produit) {

		ArrayList<Commande> parPdt = new ArrayList<Commande>();
		ArrayList<Produit> listeP = new ArrayList<Produit>();

		int index;
		for (Commande commande : commandes) {
			listeP.addAll((Collection<? extends Produit>) commande.getProduits().keySet());
			index = 1;
			while(listeP.get(index)!=null) {
				if( listeP.get(index).getIdProduit()==produit.getIdProduit()) {
					parPdt.add(commande);
				}
				index++;
			}
		}
		return parPdt;
	}

	@Override
	public ArrayList<Commande> getByClient(Client client) {

		ArrayList<Commande> parCli = new ArrayList<Commande>();
		for(Commande commande : commandes)
		{
			if(commande.getClient().getIdClient()==client.getIdClient()) {
				parCli.add(commande);
			}
		}
		return parCli;

	}

	@Override
	public ArrayList<Commande> getByDate(LocalDate date) {

		ArrayList<Commande> parDate = new ArrayList<Commande>();
		for(Commande commande : commandes)
		{
			if(commande.getDate().equals(date)) {
				parDate.add(commande);
			}
		}
		return parDate;

	}

}

package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.ProduitDAO;
import metier.Produit;

public class ListeMemoireProduitDAO implements ProduitDAO {

	private static ListeMemoireProduitDAO instance;

	private List<Produit> produits;


	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}

	private ListeMemoireProduitDAO() {

		this.produits = new ArrayList<Produit>();

		this.produits.add(new Produit(1, "nom", "description", "visuel", 4, 1));
		this.produits.add(new Produit(2, "nom2", "description2", "visuel2", 5, 2));
	}


	@Override
	public boolean create(Produit produit) {

		produit.setIdProduit(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.produits.contains(produit)) {

			produit.setIdProduit(produit.getIdProduit() + 1);
		}
		return this.produits.add(produit);

	}

	@Override
	public boolean update(Produit produit) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.produits.indexOf(produit);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une Produit inexistante");
		} else {

			this.produits.set(idx, produit);
		}

		return true;
	}

	@Override
	public boolean delete(Produit produit) {

		Produit supprime;

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.produits.indexOf(produit);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une Produit inexistante");
		} else {
			supprime = this.produits.remove(idx);
		}

		return produit.equals(supprime);
	}

	@Override
	public Produit getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.produits.indexOf(new Produit(id, "nom3", "description3", "visuel3", 445, 5));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune Produit ne possède cet identifiant");
		} else {
			return this.produits.get(idx);
		}
	}

	@Override
	public ArrayList<Produit> findAll() {
		return (ArrayList<Produit>) this.produits;
	}




	

	@Override
	public ArrayList<Produit> getByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produit> getByDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produit> getByTarif(float tarif) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produit> getByVisuel(String visuel) {
		// TODO Auto-generated method stub
		return null;
	}
}


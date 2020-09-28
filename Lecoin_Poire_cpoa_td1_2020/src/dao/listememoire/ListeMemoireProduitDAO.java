package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import dao.ProduitDAO;
import metier.Categorie;
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

		Categorie categorie = new Categorie(1, "titre", "visuel");
		this.produits.add(new Produit(1, "nom", "description", "visuel", 4, categorie));
		this.produits.add(new Produit(2, "nom2", "description2", "visuel2", 5, categorie));
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
		CategorieDAO categoriedao = ListeMemoireCategorieDAO.getInstance();

		int idx = this.produits.indexOf(new Produit(id, "nom3", "description3", "visuel3", 445, categoriedao.getById(2)));
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
		ArrayList<Produit> lesNoms=new ArrayList<Produit>();
		for(Produit produit : produits)
		{
			if(produit.getNom().equals(nom)) {
				lesNoms.add(produit);
			}
		}
		return lesNoms;
	}

	@Override
	public ArrayList<Produit> getByDescription(String description) {
		ArrayList<Produit> lesDescriptions=new ArrayList<Produit>();
		for(Produit produit : produits)
		{
			if(produit.getDescription().equals(description)) {
				lesDescriptions.add(produit);
			}
		}
		return lesDescriptions;
	}

	@Override
	public ArrayList<Produit> getByTarif(float tarif) {
		ArrayList<Produit> lesTarifs=new ArrayList<Produit>();
		for(Produit produit : produits)
		{
			if(produit.getTarif()==tarif) {
				lesTarifs.add(produit);
			}
		}
		return lesTarifs;
	}

	@Override
	public ArrayList<Produit> getByVisuel(String visuel) {
		ArrayList<Produit> lesVisuels=new ArrayList<Produit>();
		for(Produit produit : produits)
		{
			if(produit.getVisuel().equals(visuel)) {
				lesVisuels.add(produit);
			}
		}
		return lesVisuels;
	}
}

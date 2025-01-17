package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import metier.Categorie;

public class ListeMemoireCategorieDAO implements CategorieDAO {

	private static ListeMemoireCategorieDAO instance;

	private List<Categorie> categories;


	public static ListeMemoireCategorieDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}

		return instance;
	}

	private ListeMemoireCategorieDAO() {

		this.categories = new ArrayList<Categorie>();
	}


	@Override
	public boolean create(Categorie categorie) {

		categorie.setIdCategorie(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.categories.contains(categorie)) {

			categorie.setIdCategorie(categorie.getIdCategorie() + 1);
		}
		return this.categories.add(categorie);
	}

	@Override
	public boolean update(Categorie categorie) {

		// Ne fonctionne que si l'objet métier est bien fait...
		try {
			this.categories.set(categorie.getIdCategorie(), categorie);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Categorie categorie) {

		Categorie supprime;

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.categories.indexOf(categorie);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} else {
			supprime = this.categories.remove(idx);
		}

		return categorie.equals(supprime);
	}

	@Override
	public Categorie getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.categories.indexOf(new Categorie(id, "test", "test.png"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne possède cet identifiant");
		} else {
			return this.categories.get(idx);
		}
	}

	@Override
	public ArrayList<Categorie> findAll() {

		ArrayList<Categorie> categories = new ArrayList<>(this.categories);
		return categories;
	}

	@Override
	public ArrayList<Categorie> getByTitre(String titre) {
		ArrayList<Categorie> lesTitres=new ArrayList<Categorie>();
		for(Categorie categorie : categories)
		{
			if(categorie.getTitre().equals(titre)) {
				lesTitres.add(categorie);
			}
		}
		return lesTitres;
	}

	@Override
	public ArrayList<Categorie> getByVisuel(String visuel) {
		ArrayList<Categorie> lesTitres=new ArrayList<Categorie>();
		for(Categorie categorie : categories)
		{
			if(categorie.getVisuel().equals(visuel)) {
				lesTitres.add(categorie);
			}
		}
		return lesTitres;
	}
}


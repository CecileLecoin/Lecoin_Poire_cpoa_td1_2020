package tests.dao;

import junit.framework.TestCase;
import metier.Categorie;
import metier.Client;
import metier.Commande;
import metier.Produit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import dao.enumeration.Persistence;
import daoFactory.DAOFactory;

public class MySQLCommandeDAOTest extends TestCase{

	private DAOFactory dao;
    private Commande commande;

	@Before
	@Override
	public void setUp() {

		dao = DAOFactory.getDaoFactory(Persistence.MYSQL);
        // assertNotNull(dao);
		assertNotNull(dao.getCommandeDAO().findAll());

		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateDebut = LocalDate.parse("2020-09-02 13:12:00", formatage);

		Client client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");

		HashMap<Produit, Integer> produitsHM = new HashMap<>();
		ArrayList<Produit> produits = new ArrayList<Produit>();
		Categorie categorie = new Categorie(1, "titre", "visuel");
		produits.add(new Produit(1, "nom", "description", "visuel", 4, categorie));
		produits.add(new Produit(2, "nom2", "description2", "visuel2", 5, categorie));
		produitsHM.put(produits.get(1), 2);
		produitsHM.put(produits.get(1), 4);

		commande = new Commande(1, dateDebut, client, produitsHM);
	}

    @Test
    public void testEquals() {
		Commande commande2 = new Commande();
		System.out.println(commande.toString());
		System.out.println(commande2.toString());
		assertEquals(commande, commande2);
	}

	@Test
    public void testGetById() {
		assertTrue(dao.getCommandeDAO().create(commande));
		Commande commande2 =dao.getCommandeDAO().getById(commande.getIdCommande());
		System.out.println(commande.toString());
		System.out.println(commande2.toString());
		assertEquals(commande, commande2);
		assertTrue(dao.getCommandeDAO().delete(commande));
	}

}

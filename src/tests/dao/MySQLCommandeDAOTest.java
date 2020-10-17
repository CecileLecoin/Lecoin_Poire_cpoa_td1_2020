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
	private DateTimeFormatter formatage;
	private Categorie categorie;
	private Produit p1;
	private Produit p2;

	@Before
	@Override
	public void setUp() {

		categorie = new Categorie(1, "titre", "visuel");
		p1 = new Produit(8, "testCrea", "description", "visuel", 0, categorie);
		p2 = new Produit(9, "nom2", "description2", "visuel2", 5, categorie);
		formatage = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		dao = DAOFactory.getDaoFactory(Persistence.MYSQL);
		assertNotNull(dao.getCommandeDAO().findAll());


		LocalDate dateDebut = LocalDate.parse("2020-09-02 13:12:00", formatage);
		//Le ptn de formatage fonctionne pas ses grands morts
		Client client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");

		HashMap<Produit, Integer> produitsHM = new HashMap<>();
		produitsHM.put(p1, 2);

		commande = new Commande(1, LocalDate.now(), client, produitsHM);
	}

	@Test
    public void testGetById() {

		assertTrue(dao.getCategorieDAO().create(categorie));
		assertTrue(dao.getProduitDAO().create(p1));
		assertTrue(dao.getCommandeDAO().create(commande));
		Commande commande2 =dao.getCommandeDAO().getById(commande.getIdCommande());

		System.out.println("comparaison commande 1 et commande 2");
		System.out.println(commande.toString());
		System.out.println(commande2.toString());

		assertEquals(commande, commande2);
		assertTrue(dao.getCommandeDAO().delete(commande));
		assertTrue(dao.getProduitDAO().delete(p1));
		assertTrue(dao.getCategorieDAO().delete(categorie));
	}

	@Test
    public void testCreate() {
        System.out.println("\n----- \ntestCreate");

        int size = dao.getCommandeDAO().findAll().size();

        assertTrue(dao.getProduitDAO().create(p1));
        assertTrue(dao.getCommandeDAO().create(commande));
        System.out.println(String.format("list size : before : %d after : %d ", size, dao.getCommandeDAO().findAll().size()));
        assertEquals(++size, dao.getCommandeDAO().findAll().size());

        //Suppression du commande créer par le test
        dao.getCommandeDAO().delete(commande);
        assertTrue(dao.getProduitDAO().delete(p1));
	}

	@Test
    public void testUpdate() {
        System.out.println("\n----- \ntestUpdate");

		assertTrue(dao.getCategorieDAO().create(categorie));
        assertTrue(dao.getProduitDAO().create(p1));
        dao.getCommandeDAO().create(commande);

        commande.setDate(LocalDate.parse("2000-01-01 01:01:00", formatage));
        commande.setClient(new Client(2, "Unom", "Uprenom", "Uidentifiant", "Umdp", "Unum", "Uvoie", "Ucp", "Uville", "Upays"));
		HashMap<Produit, Integer> HM = new HashMap<>();
		HM.put(p2, 1);
		commande.setProduits(HM);

		assertTrue(dao.getProduitDAO().create(p2));
        assertTrue(dao.getCommandeDAO().update(commande));

        // Suppression du produit créer par le test
        dao.getCommandeDAO().delete(commande);
        assertTrue(dao.getProduitDAO().delete(p1));
        assertTrue(dao.getProduitDAO().delete(p2));
		assertTrue(dao.getCategorieDAO().delete(categorie));
	}

	@Test
    public void testDelete() {
        System.out.println("\n----- \ntestDelete");

        dao.getCommandeDAO().create(commande);
        assertTrue(dao.getCommandeDAO().delete(commande));
    }

}

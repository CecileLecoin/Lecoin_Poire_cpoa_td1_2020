package tests.dao;

import org.junit.Before;
import org.junit.Test;

import dao.CategorieDAO;
import dao.ProduitDAO;
import dao.enumeration.Persistence;
import daoFactory.DAOFactory;
import junit.framework.TestCase;
import metier.Categorie;
import metier.Produit;

public class ListeMemoireProduitDAOTest extends TestCase {

    private ProduitDAO dao;
    private Produit produit;

    @Before
    @Override
    public void setUp() {

        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE).getProduitDAO();
        assertNotNull(dao);
        assertNotNull(dao.findAll());

        Categorie categorie = new Categorie(1, "titre", "visuel");
        produit = new Produit(1, "nom", "description", "visuel", 0.5f, categorie);
    }

    @Test
    public void testCreate() {
        System.out.println("\n----- \ntestCreate");

        int size = dao.findAll().size();

        assertTrue(dao.create(produit));
        System.out.println(String.format("list size : before : %d after : %d ", size, dao.findAll().size()));
        assertEquals(++size, dao.findAll().size());

        // Suppression du produit créer par le test
        dao.delete(produit);
    }

    @Test
    public void testUpdate() {
        System.out.println("\n----- \ntestUpdate");

        dao.create(produit);
        System.out.println("Before : " + dao.getById(produit.getIdProduit()));

        produit.setDescription("descriptionUpdate");
        produit.setNom("nomUpdate");
        produit.setVisuel("visuelUpdate");

        assertTrue(dao.update(produit));
        System.out.println("After : " + dao.getById(produit.getIdProduit()));

        // Suppression du produit créer par le test
        dao.delete(produit);
    }

    @Test
    public void testDelete() {
        System.out.println("\n----- \ntestDelete");

        dao.create(produit);
        assertTrue(dao.delete(produit));
    }
}

package tests.dao;

import exceptions.CommandeApplicationException;
import org.junit.Before;
import org.junit.Test;

import dao.CategorieDAO;
import dao.enumeration.Persistence;
import daofactory.DAOFactory;
import junit.framework.TestCase;
import metier.Categorie;

public class MySQLCategorieDAOTest extends TestCase{

    private CategorieDAO dao;
    private Categorie categorie;

    @Before
    @Override
    public void setUp() throws CommandeApplicationException {

        dao = DAOFactory.getDaoFactory(Persistence.MYSQL).getCategorieDAO();
        assertNotNull(dao);
        assertNotNull(dao.findAll());

        categorie = new Categorie(1, "titre", "visuel");
    }

    @Test
    public void testGetById() throws CommandeApplicationException {

        assertTrue(dao.create(categorie));
        Categorie ct2 =dao.getById(categorie.getIdCategorie());

        assertEquals(categorie, ct2);
        assertTrue(dao.delete(categorie));
    }

    @Test
    public void testCreate() throws CommandeApplicationException {
        System.out.println("\n----- \ntestCreate");

        int size = dao.findAll().size();

        assertTrue(dao.create(categorie));
        System.out.println(String.format("list size : before : %d after : %d ", size, dao.findAll().size()));
        assertEquals(++size, dao.findAll().size());

        // Suppression du produit créer par le test
        dao.delete(categorie);
    }

    @Test
    public void testUpdate() throws CommandeApplicationException {
        System.out.println("\n----- \ntestUpdate");

        dao.create(categorie);
        System.out.println("Before : " + dao.getById(categorie.getIdCategorie()));

        categorie.setTitre("titreUpdate");
        categorie.setVisuel("visuelUpdate");

        assertTrue(dao.update(categorie));
        System.out.println("After : " + dao.getById(categorie.getIdCategorie()));

        // Suppression du produit créer par le test
        dao.delete(categorie);
    }

    @Test
    public void testDelete() throws CommandeApplicationException {
        System.out.println("\n----- \ntestDelete");

        dao.create(categorie);
        assertTrue(dao.delete(categorie));
    }

}

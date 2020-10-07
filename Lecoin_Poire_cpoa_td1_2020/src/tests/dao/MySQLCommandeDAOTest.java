package tests.dao;

import junit.framework.TestCase;
import metier.Commande;

import org.junit.Before;
import org.junit.Test;

import dao.CommandeDAO;
import dao.enumeration.Persistence;
import daoFactory.DAOFactory;

public class MySQLCommandeDAOTest extends TestCase{

	private DAOFactory dao;
    private Commande commande;

	@Before
	public void setUp() {

		dao = DAOFactory.getDaoFactory(Persistence.MYSQL);
        //assertNotNull(dao);
		assertNotNull(dao.getCommandeDAO().findAll());
		

		commande = new Commande(1, ""); 
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
		Commande commande2 = new Commande();
		System.out.println(commande.toString());
		System.out.println(commande2.toString());
		assertEquals(commande, commande2);
	}

}

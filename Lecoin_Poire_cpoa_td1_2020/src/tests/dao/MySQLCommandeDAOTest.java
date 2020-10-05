package tests.dao;

import junit.framework.TestCase;
import metier.Commande;

import org.junit.Before;
import org.junit.Test;

public class MySQLCommandeDAOTest extends TestCase{

    private Commande commande;

	@Before
	public void setUp() {
		commande = new Commande();
	}

    @Test
    public void testEquals() {
		Commande commande2 = new Commande();
		System.out.println(commande.toString());
		System.out.println(commande2.toString());
		assertEquals(commande, commande2);
	}

}

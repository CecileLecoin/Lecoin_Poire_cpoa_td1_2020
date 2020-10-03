package tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import dao.*;
import metier.Commande;
import metier.Produit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MySQLCommandeDAOTest {

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

package tests.metier;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

import junit.framework.TestCase;
import metier.Categorie;
import metier.Client;
import metier.Commande;
import metier.Produit;

public class CommandeTest extends TestCase{

    private Commande commande;
    private Client client;
    private Categorie categorie;
    private Produit p1;
    private HashMap<Produit, Integer> produitsHM;


    @Before
    @Override
    public void setUp(){

        client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");
        commande = new Commande(1, client);
        categorie = new Categorie(1, "titre", "visuel");
        p1 = new Produit(1, "nom", "description", "visuel", 4, categorie);
        produitsHM = new HashMap<>();

    }

@Test
    public void testSettersAutorise() {

        try{
            commande.setDate(LocalDate.now());
		    produitsHM.put(p1, 2);
            commande.setProduits(produitsHM);

        } catch (IllegalArgumentException iae) {
            fail("Exception lancée par erreur ");
        }

        try{
            commande.addProduit(p1, 4);
            commande.supprProduit(p1);

        } catch (IllegalArgumentException iae) {
            fail("Exception lancée par erreur ");
        }

    }

    @Test
    public void testSettersNonAutorise() {

        assertThrows(IllegalArgumentException.class, () -> commande.setProduits(null));
        assertThrows(IllegalArgumentException.class, () -> commande.addProduit(null, 4));
        assertThrows(IllegalArgumentException.class, () -> commande.addProduit(p1, 0));
        assertThrows(IllegalArgumentException.class, () -> commande.supprProduit(null));
    }

    @Test
    public void testLeCalcul() {

        int quantity = 2;
        produitsHM.put(p1, quantity);
        commande.setProduits(produitsHM);
        double prix = commande.calculPrix();

        assertEquals(prix, (double)p1.getTarif()*quantity);

    }
}

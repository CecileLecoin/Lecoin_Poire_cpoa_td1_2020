package tests.metier;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import junit.framework.TestCase;
import metier.Categorie;
import metier.Produit;

public class ProduitTest extends TestCase{

    @Test
    public void testSettersAutorise() {

        try{
            Categorie categorie = new Categorie(1, "titre", "visuel");
            Produit produit = new Produit(1, "nom", "description", "visuel", 1.5f, categorie);

            produit.setDescription("de");
            produit.setNom("no");
            produit.setVisuel("vi");
            produit.setTarif(1f);

        } catch (IllegalArgumentException iae) {
            fail("Exception lancée par erreur ");
        }

    }

    @Test
    public void testSettersNonAutorise() {

        Categorie categorie = new Categorie(1, "titre", "visuel");
        Produit produit = new Produit(1, "nom", "description", "visuel", 1.5f, categorie);

        assertThrows(IllegalArgumentException.class, () -> produit.setVisuel(" "));
        assertThrows(IllegalArgumentException.class, () -> produit.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> produit.setNom("   "));
        assertThrows(IllegalArgumentException.class, () -> produit.setTarif(-1f));

    }

}

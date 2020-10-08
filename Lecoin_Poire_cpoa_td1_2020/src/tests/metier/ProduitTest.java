package tests.metier;

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

        try {
            produit.setVisuel("   ");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}

        try {
            produit.setDescription(null);
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}

        try {
            produit.setNom("   ");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}

        try {
            produit.setTarif(-1f);
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
    }

}

package tests.metier;

import org.junit.Test;

import junit.framework.TestCase;
import metier.Categorie;

public class CategorieTest extends TestCase{

    @Test
    public void testSettersAutorise() {

        try{
            Categorie categorie = new Categorie(1, "titre", "visuel");

            categorie.setTitre("t");
            categorie.setVisuel("v");

        } catch (IllegalArgumentException iae) {
            fail("Exception lancée par erreur ");
        }
    }

    @Test
    public void testSettersNonAutorise() {

        Categorie categorie = new Categorie(1, "titre", "visuel");

        try {
            categorie.setVisuel("   ");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}

        try {
            categorie.setTitre(null);
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
    }
}

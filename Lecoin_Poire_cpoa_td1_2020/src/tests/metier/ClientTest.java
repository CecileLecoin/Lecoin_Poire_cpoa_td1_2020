package tests.metier;

import org.junit.Test;

import junit.framework.TestCase;
import metier.Client;

public class ClientTest extends TestCase {

    @Test
    public void testSettersAutorise() {

        try {
            Client client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");

            client.setCp("c ");
            client.setNom("no");
            client.setNum("nu");
            client.setPays("pa");
            client.setPrenom("pre   ");
            client.setMdp("m");
            client.setVoie("v");
            client.setVille("vie");
            client.setIdentifiant("ident");

        } catch (IllegalArgumentException iae) {
            fail("Exception lancée par erreur ");
        }
    }

    @Test
    public void testSettersNonAutorise() {

        Client client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");

        try {
            client.setNom("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setPrenom("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setIdentifiant("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setMdp("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setNum("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setVoie("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setCp("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setVille("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
        try {
            client.setPays("");
            fail("Exception non lancée !");
        } catch (IllegalArgumentException iae) {}
    }
}

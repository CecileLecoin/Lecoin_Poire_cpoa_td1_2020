package tests.metier;

import static org.junit.Assert.assertThrows;

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

        assertThrows(IllegalArgumentException.class, () -> client.setNom(""));
        assertThrows(IllegalArgumentException.class, () -> client.setPrenom(""));
        assertThrows(IllegalArgumentException.class, () -> client.setIdentifiant(""));
        assertThrows(IllegalArgumentException.class, () -> client.setMdp(""));
        assertThrows(IllegalArgumentException.class, () -> client.setNum(""));
        assertThrows(IllegalArgumentException.class, () -> client.setVoie(""));
        assertThrows(IllegalArgumentException.class, () -> client.setCp(""));
        assertThrows(IllegalArgumentException.class, () -> client.setVille(""));
        assertThrows(IllegalArgumentException.class, () -> client.setPays(""));
    }
}

package tests.metier;

import java.time.LocalDate;

import org.junit.Before;

import junit.framework.TestCase;
import metier.Client;
import metier.Commande;

public class CommandeTest extends TestCase{

    @Before
    @Override
    public void setUp(){

        Client client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");
        Commande commande = new Commande(1, LocalDate.now(), client);

        
    }
}

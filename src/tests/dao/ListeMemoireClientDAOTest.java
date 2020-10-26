package tests.dao;

import exceptions.CommandeApplicationException;
import org.junit.Before;
import org.junit.Test;

import dao.ClientDAO;
import dao.enumeration.Persistence;
import daofactory.DAOFactory;
import junit.framework.TestCase;
import metier.Client;

public class ListeMemoireClientDAOTest extends TestCase{
    private ClientDAO dao;
    private Client client;

    @Before
    @Override
    public void setUp() throws CommandeApplicationException {

        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE).getClientDAO();
        assertNotNull(dao);
        assertNotNull(dao.findAll());

        client = new Client(0, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");
    }

    @Test
    public void testCreate() throws CommandeApplicationException {
        System.out.println("\n----- \ntestCreate");

        int size = dao.findAll().size();

        assertTrue(dao.create(client));
        System.out.println(String.format("list size : before : %d after : %d ", size, dao.findAll().size()));
        assertEquals(++size, dao.findAll().size());

        // Suppression du produit créer par le test
        dao.delete(client);
    }

    @Test
    public void testUpdate() throws CommandeApplicationException {
        System.out.println("\n----- \ntestUpdate");

        dao.create(client);
        System.out.println("Before : " + dao.getById(client.getIdClient()));

        client.setCp("cpUpdate");
        client.setIdentifiant("identifiantUpdate");
        client.setMdp("mdpUpdate");
        client.setNom("nomUpdate");
        client.setNum("numUp");
        client.setPrenom("prenomUpdate");
        client.setVille("villeUpdate");
        client.setVoie("voieUpdate");

        assertTrue(dao.update(client));
        System.out.println("After : " + dao.getById(client.getIdClient()));

        // Suppression du produit créer par le test
        dao.delete(client);
    }

    @Test
    public void testDelete() throws CommandeApplicationException {
        System.out.println("\n----- \ntestDelete");

        dao.create(client);
        assertTrue(dao.delete(client));
    }
}

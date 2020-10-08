package tests.dao;

import junit.framework.TestCase;

public class ListeMemoireCommandeDAOTest extends TestCase {

    private DAOFactory dao;
	private Commande commande;
	private DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Categorie categorie = new Categorie(1, "titre", "visuel");
	private Produit p1 =new Produit(1, "nom", "description", "visuel", 4, categorie);
	private Produit p2 =new Produit(2, "nom2", "description2", "visuel2", 5, categorie);


    @Before
    @Override
    public void setUp() {

        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE);
        assertNotNull(dao.getCommandeDAO());
        assertNotNull(dao.getCommandeDAO().findAll());

        LocalDate dateDebut = LocalDate.parse("2020-09-02 13:12:00", formatage);

		Client client = new Client(1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");

		HashMap<Produit, Integer> produitsHM = new HashMap<>();
		produitsHM.put(p1, 2);
		produitsHM.put(p1, 4);

		commande = new Commande(1, dateDebut, client, produitsHM);
    }

    @Test
    public void testGetById() {

		assertTrue(dao.getCommandeDAO().create(commande));
		Commande commande2 =dao.getCommandeDAO().getById(commande.getIdCommande());

		System.out.println(commande.toString());
		System.out.println(commande2.toString());

		assertEquals(commande, commande2);
		assertTrue(dao.getCommandeDAO().delete(commande));
    }

    @Test
    public void testCreate() {
        System.out.println("\n----- \ntestCreate");

        int size = dao.getCommandeDAO().findAll().size();

        assertTrue(dao.getCommandeDAO().create(commande));
        System.out.println(String.format("list size : before : %d after : %d ", size, dao.getCommandeDAO().findAll().size()));
        assertEquals(++size, dao.getCommandeDAO().findAll().size());

        //Suppression du commande créer par le test
        dao.getCommandeDAO().delete(commande);
    }

    @Test
    public void testUpdate() {
        System.out.println("\n----- \ntestUpdate");

        dao.getCommandeDAO().create(commande);
        System.out.println("Before : " + dao.getCommandeDAO().getById(commande.getIdCommande()));

        commande.setDate(LocalDate.parse("2000-01-01 01:01:00", formatage));
        commande.setClient(new Client(2, "Unom", "Uprenom", "Uidentifiant", "Umdp", "Unum", "Uvoie", "Ucp", "Uville", "Upays"));
		HashMap<Produit, Integer> HM = new HashMap<>();
		HM.put(p2, 1);
		commande.setProduits(HM);

        assertTrue(dao.getCommandeDAO().update(commande));
        System.out.println("After : " + dao.getCommandeDAO().getById(commande.getIdCommande()));

        // Suppression du produit créer par le test
        dao.getCommandeDAO().delete(commande);
    }

    @Test
    public void testDelete() {
        System.out.println("\n----- \ntestDelete");

        dao.getCommandeDAO().create(commande);
        assertTrue(dao.getCommandeDAO().delete(commande));
    }
}

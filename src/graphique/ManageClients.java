package graphique;

import java.util.Scanner;

import daofactory.DAOFactory;
import metier.Client;

public class ManageClients {

	private DAOFactory daos;

    public ManageClients(DAOFactory daos) {

    	this.daos = daos;

    	System.out.println("Que voulez vous faire avec les clients ? \n -Afficher la liste : 'liste'\n -Afficher un client : 'client'\n"
				+ " -Ajouter un client : 'ajout'\n - Supprimer un client : 'suppr'\n -Modifier les informations d'un client : 'modif'\n");

    	 Scanner sc = new Scanner(System.in);
         String choice = sc.nextLine();
         choice = choice.toLowerCase();

         switch(choice) {
			case "afficher la liste" : case "liste" : case "la liste":
				for ( Client client : daos.getClientDAO().findAll()) {
					System.out.println(client);
				}
				break;

			case "afficher un client": case "client" : case "un client":
				System.out.println("Rentrez le numero client \n");

				System.out.println(daos.getClientDAO().getById(sc.nextInt()));
				break;

			case "add": case "ajouter" : case "ajout":
				ajoutClient();
				break;

			case "supprimer un client": case "supprimer" : case "suppr":
				supprClient();
				break;

			case "modifier un client": case "modifier" : case "modif":
				modifClient();
				break;
         }

         sc.close();
    }

    public void ajoutClient() {
    	System.out.println("Nouveau client : \n");

    	Scanner sc = new Scanner(System.in);

    	System.out.println("Nom : \n");
		String nom = sc.nextLine();
		System.out.println("Prenom : \n");
		String prenom = sc.nextLine();
		System.out.println("identifiant : \n");
		String id = sc.nextLine();
		System.out.println("Mot de passe : \n");
		String mdp = sc.nextLine();
		System.out.println("Numero de rue : \n");
		String numRue = sc.nextLine();
		System.out.println("Voie : \n");
		String voie = sc.nextLine();
		System.out.println("Code postal : \n");
		String cp = sc.nextLine();
		System.out.println("Ville : \n");
		String ville = sc.nextLine();
		System.out.println("Pays : \n");
		String pays = sc.nextLine();

		Client client = new Client(0, nom, prenom, id, mdp, numRue, voie, cp, ville, pays);

		daos.getClientDAO().create(client);
		System.out.println("recap du client : " + client);

		sc.close();
    }

    public void supprClient() {
    	System.out.println("Numero du client a modifier : \n");

    	Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Client client = daos.getClientDAO().getById(id);

        System.out.println(String.format("Supprimer le client : %s  ? (o/n) %n", client));
        if (sc.nextLine().equals("o")) {
            daos.getClientDAO().delete(client);
        }

        sc.close();
    }

    public void modifClient() {
    	System.out.println("Numero du client a modifier : \n");

    	Scanner sc= new Scanner(System.in);
    	int id = sc.nextInt();

    	Client client = daos.getClientDAO().getById(id);

		daos.getClientDAO().update(client);
		sc.close();
    }
}

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

import database.ClientSQL;
import metier.Client;

public class Main {

	public static void main(String[] args) {

		System.out.println("Bienvenue dans la gestion de votre boutique \n Voulez vous gerer les produit, clients ou encore les categories ?");
		Scanner sc = new Scanner(System.in);
		String choice;
		choice = sc.nextLine();
		//sc.close();
		choice.toLowerCase();

		System.out.println(choice);

		switch (choice) {
			case "produit": case "produits" : case "les produits":
				// action sur les produits ->  choix de suppr add modif ...
				break;
				
			case "client": case "clients": case "les clients":
				System.out.println("Que voulez vous faire avec les clients ? \n -Afficher la liste : 'liste'\n -Afficher un client : 'client'\n"
						+ " -Ajouter un client : 'ajout'\n - Supprimer un client : 'suppr'\n -Modifier les informations d'un client : 'modif'\n");
				choice="";
				choice = sc.nextLine();
				choice.toLowerCase();
				switch(choice) {
					case "afficher la liste" : case "liste" : case "la liste":
						ArrayList<Client> c= ClientSQL.listClient();
						for(Client disp : c) {
							System.out.println(disp.getIdClient() +disp.getPrenom() + " " + disp.getNom() + "\n");
						}
					break;
					
					case "afficher un client": case "client" : case "un client": 
						System.out.println("Rentrez le numéro client \n");
						int idC = sc.nextInt();

						System.out.println(idC);
						Client disp = ClientSQL.getById(idC);
						System.out.println("Fiche info client : \n" + disp.getPrenom() + " " + disp.getNom());
					break;
					
					case "ajouter un client": case "ajouter" : case "ajout": 
						System.out.println("Nouveau client : \n");
						
						System.out.println("Nom : \n");
						String a = sc.nextLine();
						System.out.println("Prénom : \n");
						String b = sc.nextLine();
						// CODE A SUPPRIMER QD ON GERERA PLUS QUE JUSTE LE NOM ET LE PRENOM
						String j = " ";
						String d = " ";
						String e = " ";
						String f = " ";
						String g = " ";
						String h = " ";
						String i = " ";
						/*System.out.println("identifiant : \n");
						String c = sc.nextLine();
						System.out.println("Mot de passe : \n");
						String d = sc.nextLine();
						System.out.println("Numero de rue : \n");
						String e = sc.nextLine();
						System.out.println("Voie : \n");
						String f = sc.nextLine();
						System.out.println("Code postal : \n");
						String g = sc.nextLine();
						System.out.println("Ville : \n");
						String h = sc.nextLine();
						System.out.println("Pays : \n");
						String i = sc.nextLine();*/
						
						sc.close();
						ArrayList<Client> newC= ClientSQL.listClient();
						int idNewC = newC.size()+1;
						Client clientAjoute = new Client(idNewC, a, b, j, d, e, f,
								g, h, i);
						ClientSQL.ajoutClient(clientAjoute);
						System.out.println("Fiche recap du nouveau client : \n" + a + " " + b);
					break;
					
					case "supprimer un client": case "supprimer" : case "suppr": 
						System.out.println("Client à supprimer : \n");
						System.out.println("Num id client : \n");
						int supN = sc.nextInt();
						System.out.println("Supprimer ce client ? (o/n) \n");
						Client dispC = ClientSQL.getById(supN);
						System.out.println(dispC.getNom() + dispC.getPrenom());
						String reponse = sc.nextLine();
						if(reponse=="o")
						{
						Client clientSupprime = new Client(supN, " ", " ", null, null, null, null,
								null, null, null);
						ClientSQL.ajoutClient(clientSupprime);
						}
				
				break;
				
			case "modifier un client": case "modifier" : case "modif": 
				System.out.println("Numéro du client à modifier : \n");
				int idCm = sc.nextInt();
				sc.close();

				Client clientMod = ClientSQL.getById(idCm);
				System.out.println("Supprimer ce client ? (oui/non) \n");
				System.out.println(clientMod.getNom() + " " +clientMod.getPrenom()+"\n");
				System.out.println("avant le scan ");
				Scanner scan = new Scanner(System.in);
				String reponseA = scan.nextLine();
				System.out.println("après le scan ");

				if(reponseA=="oui")
				{
					System.out.println("dans le if ");
					System.out.println("Nouveau nom : \n");
					String aM = sc.nextLine();
					System.out.println("Nouveau prénom : \n");
					String bM = sc.nextLine();
					sc.close();
					/*System.out.println("identifiant : \n");
					String c = sc.nextLine();
					System.out.println("Mot de passe : \n");
					String d = sc.nextLine();
					System.out.println("Numero de rue : \n");
					String e = sc.nextLine();
					System.out.println("Voie : \n");
					String f = sc.nextLine();
					System.out.println("Code postal : \n");
					String g = sc.nextLine();
					System.out.println("Ville : \n");
					String h = sc.nextLine();
					System.out.println("Pays : \n");
					String i = sc.nextLine();*/
				ClientSQL.modifClient(clientMod, aM, bM);
				System.out.println("Fiche recap du client : \n" + aM + " " + bM);
				}
				System.out.println("après le if ");
			break;
				
			case "categorie": case "categories": case "les categories":
				// action sur les categories -> choix de suppr add modif ...
				break;
				
			default:
				System.out.println("Selection non reconnue");
				
				
		}
	}

}
}

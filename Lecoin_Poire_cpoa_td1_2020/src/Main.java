import java.util.ArrayList;
import java.util.Scanner;

import database.CategorieSQL;
import database.ClientSQL;
import database.ProduitSQL;
import metier.Categorie;
import metier.Client;
import metier.Produit;

public class Main {
	
	public static int calcId(int tab[]) {
		boolean trouve = false;
		int iterateur=1;
		while (iterateur <= tab.length+1 && trouve==false) {
			if (Integer.compareUnsigned(tab[iterateur], iterateur)==0) {
				trouve = true;
				return iterateur;
			}
			iterateur++;
		} return tab.length+1;
	}

	public static void main(String[] args) {

		System.out.println("Bienvenue dans la gestion de votre boutique \n Voulez vous gerer les produit, clients ou encore les categories ?");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		choice = choice.toLowerCase();

		ArrayList<Produit> produits = ProduitSQL.listProduit();
		ArrayList<Categorie> categories = CategorieSQL.listCategorie();
		
		int tabId[];

		switch (choice) {
			case "produit": case "produits" : case "prod":
				System.out.println("Que voulez vous faire avec les produit ? \n -Afficher la liste : 'liste'\n -Afficher un produit : 'produit'\n"
						+ " -Ajouter un produit : 'ajout'\n - Supprimer un produit : 'suppr'\n -Modifier les informations d'un produit : 'modif'\n");
				choice = sc.nextLine();
				choice = choice.toLowerCase();

				switch(choice) {
					case "afficher la liste" : case "liste" : case "la liste":
						for(Produit po : produits) {
							System.out.println(po.toString());
						}
						break;

					case "afficher un produit": case "produit" : case "un produit":
						System.out.println("Rentrez le numero produit \n");

						Produit disp = ProduitSQL.getById(sc.nextInt());
						System.out.println(disp.toString());
						break;

					case "add": case "ajouter" : case "ajout":
						System.out.println("Nouveau produit : \n");

						System.out.println("Nom : \n");
						String nomProduit = sc.nextLine();
						System.out.println("description : \n");
						String descriptionProduit = sc.nextLine();
						System.out.println("tarif : \n");
						float tarifProduit = sc.nextFloat();
						sc.nextLine();
						System.out.println("visuel : \n");
						String visuelProduit = sc.nextLine();


						int iterateur = 0;
						tabId = new int[produits.size()];
						for(Produit produit : produits) {
							tabId[iterateur] = produit.getIdProduit();
						}
						int idProd = calcId(tabId);
						
						Produit produit = new Produit(idProd, nomProduit, descriptionProduit, visuelProduit, tarifProduit);

						ProduitSQL.ajoutProduit(produit);
						produits.add(produit);

						System.out.println("Fiche recap du nouveau produit : \n" + nomProduit + " " + tarifProduit);
						break;

					case "supprimer un produit": case "supprimer" : case "suppr":
						System.out.println("ID du produit à supprimer : ");
						int idProdSupr = sc.nextInt();
						sc.nextLine();

						Produit produitSuppr = ProduitSQL.getById(idProdSupr);
						System.out.println(String.format("Supprimer le produit : %s %s ? (o/n) %n", produitSuppr.getNom(), produitSuppr.getTarif()));

						if(sc.nextLine().equals("o")) {
						ProduitSQL.supprProduit(produitSuppr);
						}
						break;

					case "modifier un produit": case "modifier" : case "modif":
						System.out.println("Numero du produit a modifier : \n");
						int idProdModif = sc.nextInt();
						sc.nextLine();

						Produit prodModif = ProduitSQL.getById(idProdModif);
						System.out.println(String.format("Modifier le produit : %s %s ? (o/n) %n", prodModif.getNom(),
								prodModif.getTarif()));


						if(sc.nextLine().equals("o"))
						{
							System.out.println("Nouveau nom : \n");
							prodModif.setNom(sc.nextLine());
							System.out.println("Nouvelle description : \n");
							prodModif.setDescription(sc.nextLine());
							System.out.println("Nouveau tarif : \n");
							prodModif.setTarif(sc.nextFloat());
							sc.nextLine();
							System.out.println("Nouveau visuel : \n");
							prodModif.setVisuel(sc.nextLine());


							ProduitSQL.modifProduit(prodModif);
							System.out.println(String.format("Récap : nom %s tarif %s", prodModif.getNom(), prodModif.getTarif()));
						}
						break;
					}
				break;

			case "client": case "clients": case "les clients":
				System.out.println("Que voulez vous faire avec les clients ? \n -Afficher la liste : 'liste'\n -Afficher un client : 'client'\n"
						+ " -Ajouter un client : 'ajout'\n - Supprimer un client : 'suppr'\n -Modifier les informations d'un client : 'modif'\n");

				choice = sc.nextLine();
				choice = choice.toLowerCase();
				switch(choice) {
					case "afficher la liste" : case "liste" : case "la liste":
						ArrayList<Client> clients= ClientSQL.listClient();
						for(Client cl : clients) {
							System.out.println(cl.getIdClient() +cl.getPrenom() + " " + cl.getNom() + "\n");
						}
						break;

					case "afficher un client": case "client" : case "un client":
						System.out.println("Rentrez le numero client \n");

						Client disp = ClientSQL.getById(sc.nextInt());
						System.out.println("Fiche info client : \n" + disp.getPrenom() + " " + disp.getNom());
						break;

					case "add": case "ajouter" : case "ajout":
						System.out.println("Nouveau client : \n");

						System.out.println("Nom : \n");
						String nomClient = sc.nextLine();
						System.out.println("Prenom : \n");
						String prenomClient = sc.nextLine();
						// CODE A SUPPRIMER QD ON GERERA PLUS QUE JUSTE LE NOM ET LE PRENOM
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

						ArrayList<Client> newC= ClientSQL.listClient();
						int iterateur = 0;
						tabId = new int[newC.size()];
						for(Client client : newC) {
							tabId[iterateur] = client.getIdClient();
						}
						int idNewC = calcId(tabId);

						
						Client clientAjoute = new Client(idNewC, nomClient, prenomClient, " ", " ", " ", " ",
								" ", " ", " ");
						ClientSQL.ajoutClient(clientAjoute);
						System.out.println("Fiche recap du nouveau client : \n" + nomClient + " " + prenomClient);
						break;

					case "supprimer un client": case "supprimer" : case "suppr":
						System.out.println("ID du client à supprimer : ");
						int supN = sc.nextInt();
						sc.nextLine();

						Client clientSuppr = ClientSQL.getById(supN);
						System.out.println(String.format("Supprimer le client : %s %s ? (o/n) %n", clientSuppr.getNom(), clientSuppr.getPrenom()));

						if(sc.nextLine().equals("o")) {
						ClientSQL.supprClient(clientSuppr);
						}
						break;

					case "modifier un client": case "modifier" : case "modif":
						System.out.println("Numero du client a modifier : \n");
						sc.nextLine();

						Client clientMod = ClientSQL.getById(sc.nextInt());
						System.out.println(String.format("Modifier le client : %s %s ? (o/n) %n", clientMod.getNom(),
								clientMod.getPrenom()));


						if(sc.nextLine().equals("o"))
						{
							System.out.println("Nouveau nom : \n");
							String aM = sc.nextLine();
							System.out.println("Nouveau prenom : \n");
							String bM = sc.nextLine();
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
						break;
				}
				break;

			case "categorie": case "categories": case "cat":
				System.out.println("Que voulez vous faire avec les categoris ? \n -Afficher la liste : 'liste'\n -Afficher une categorie : 'categorie'\n"
						+ " -Ajouter une categorie : 'ajout'\n - Supprimer une categorie : 'suppr'\n -Modifier les informations d'une categorie : 'modif'\n");
				choice = sc.nextLine();
				choice = choice.toLowerCase();

				switch(choice) {
					case "afficher la liste" : case "liste" : case "la liste":
						for(Categorie ca : categories) {
							System.out.println(ca.toString());
						}
						break;

					case "afficher une categorie": case "categorie" : case "cat":
						System.out.println("Rentrez le numero categorie \n");

						Categorie disp = CategorieSQL.getById(sc.nextInt());
						System.out.println(disp.toString());
						break;

					case "add": case "ajouter" : case "ajout":
						System.out.println("Nouvelle categorie : \n");

						System.out.println("titre : \n");
						String titreCategorie = sc.nextLine();
						System.out.println("visuel : \n");
						String visuelCategorie = sc.nextLine();

						int iterateur = 0;
						tabId = new int[categories.size()];
						for(Categorie categorie : categories) {
							tabId[iterateur] = categorie.getIdCategorie();
						}
						int idCategorie = calcId(tabId);
						
						Categorie categorie = new Categorie(idCategorie, titreCategorie, visuelCategorie);

						CategorieSQL.ajoutCategorie(categorie);
						categories.add(categorie);

						System.out.println("Fiche recap de la new categorie : \n" + titreCategorie);
						break;

					case "supprimer une categorie": case "supprimer" : case "suppr":
						System.out.println("ID de la categorie à supprimer : ");
						int idCateSupr = sc.nextInt();
						sc.nextLine();

						Categorie cateSuppr = CategorieSQL.getById(idCateSupr);
						System.out.println(String.format("Supprimer la categorie : %s %s ? (o/n) %n", cateSuppr.getTitre(), cateSuppr.getVisuel()));

						if(sc.nextLine().equals("o")) {
						CategorieSQL.supprCategorie(cateSuppr);
						}
						break;

					case "modifier une categorie": case "modifier" : case "modif":
						System.out.println("Numero de la categorie a modifier : \n");
						int idCateModif = sc.nextInt();
						sc.nextLine();

						Categorie cateModif = CategorieSQL.getById(idCateModif);
						System.out.println(String.format("Modifier la categorie : %s %s ? (o/n) %n", cateModif.getTitre(),
								cateModif.getVisuel()));

						if(sc.nextLine().equals("o"))
						{
							System.out.println("Nouveau titre : \n");
							cateModif.setTitre(sc.nextLine());
							System.out.println("Nouvelle visuel : \n");
							cateModif.setVisuel(sc.nextLine());

							CategorieSQL.modifCategorie(cateModif);
							System.out.println(String.format("Récap : titre %s visuel %s", cateModif.getTitre(), cateModif.getVisuel()));
						}
						break;
				}
				break;

			default:
				System.out.println("Selection non reconnue");
		}
		sc.close();
	}

}

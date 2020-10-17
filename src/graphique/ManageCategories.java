package graphique;

import java.util.Scanner;

import daofactory.DAOFactory;
import metier.Categorie;

public class ManageCategories {

	private DAOFactory daos;

    public ManageCategories(DAOFactory daos) {

    	this.daos = daos;

    	System.out.println("Que voulez vous faire avec les categoris ? \n -Afficher la liste : 'liste'\n -Afficher une categorie : 'categorie'\n"
				+ " -Ajouter une categorie : 'ajout'\n - Supprimer une categorie : 'suppr'\n -Modifier les informations d'une categorie : 'modif'\n");

    	Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        choice = choice.toLowerCase();

        switch(choice) {
			case "afficher la liste" : case "liste" : case "la liste":
				for(Categorie categorie : daos.getCategorieDAO().findAll() ) {
					System.out.println(categorie);
				}
				break;

			case "afficher une categorie": case "categorie" : case "cat":
				System.out.println("Rentrez le numero categorie \n");

				System.out.println(daos.getCategorieDAO().getById(sc.nextInt()));
				break;

			case "add": case "ajouter" : case "ajout":
				ajoutCategorie();
				break;

			case "supprimer une categorie": case "supprimer" : case "suppr":
				supprCategorie();
				break;

			case "modifier une categorie": case "modifier" : case "modif":
				break;
		}
		sc.close();
    }

    public void ajoutCategorie() {
    	System.out.println("Nouvelle categorie : \n");
    	Scanner sc = new Scanner(System.in);

    	System.out.println("titre : \n");
		String titre = sc.nextLine();
		System.out.println("visuel : \n");
		String visuel = sc.nextLine();

		Categorie categorie = new Categorie(0, titre, visuel);
		daos.getCategorieDAO().create(categorie);

		sc.close();
    }

    public void supprCategorie() {
    	System.out.println("ID de la categorie à supprimer : ");
    	Scanner sc = new Scanner(System.in);

    	daos.getCategorieDAO().delete(daos.getCategorieDAO().getById(sc.nextInt()));

    	sc.close();
    }

    public void modifCategorie() {
    	System.out.println("Numero de la categorie a modifier : \n");
        Scanner sc = new Scanner(System.in);

        int id = sc.nextInt();
        sc.nextLine();

        Categorie categorie = daos.getCategorieDAO().getById(id);
        System.out.println("Voulez vous modif cette categorie  : %s ? (o/n) %n" + categorie);

        if(sc.nextLine().equals("o"))
		{
			System.out.println("Nouveau titre : \n");
			categorie.setTitre(sc.nextLine());
			System.out.println("Nouvelle visuel : \n");
			categorie.setVisuel(sc.nextLine());

			daos.getCategorieDAO().update(categorie);
			System.out.println(String.format("Récap de la categorie : %s", categorie));
		}
		sc.close();
    }
}

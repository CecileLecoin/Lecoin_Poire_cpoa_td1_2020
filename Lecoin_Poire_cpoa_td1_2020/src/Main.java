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
		sc.close();
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
				sc.close();
				choice.toLowerCase();
				switch(choice) {
					case "Afficher la liste": case "liste" : case "la liste":
						ArrayList<Client> c= ClientSQL.listClient();
						for(Client disp : c) {
							System.out.println(disp + "\n");
						}
					break;
				
				}
				break;
				
			case "categorie": case "categories": case "les categories":
				// action sur les categories -> choix de suppr add modif ...
				break;
				
			default:
				System.out.println("Selection non reconnue");
		}
	}

}

import java.text.Normalizer;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("Bienvenue dans la gestion de votre boutique \n Voulez vous gérez les produit, clients ou encore les categories ?");
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
				// action sur les clients -> choix de suppr add modif ...
				break;
			case "categorie": case "categories": case "les categories":
				// action sur les categories -> choix de suppr add modif ...
				break;
			default:
				System.out.println("Selection non reconnue");
		}
	}

}

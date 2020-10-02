package graphique;

import java.time.LocalDate;
import java.util.Scanner;

import daoFactory.DAOFactory;
import metier.Commande;

public class ManageCommande {

    private DAOFactory daos;
	
	public ManageCommande(DAOFactory daos) {
		
		this.daos = daos;
		
		System.out.println("Que voulez vous faire avec les commandes ? \n -Afficher la liste : 'liste'\n -Afficher une commande : 'commande'\n"
				+ " -Ajouter une commande : 'ajout'\n - Gerer une commande : 'gerer' ");
		Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        choice = choice.toLowerCase();
		
		switch(choice) {
			case "liste":
				for (Commande commande : daos.getCommandeDAO().findAll()) {
					System.out.println(commande);
				}
				break;
				
			case "commande":
				System.out.println("Rentrez le numero commande \n");
				
				daos.getCommandeDAO().getById(sc.nextInt());
				break;
				
			case "ajout":
				ajoutCommande();
				break;
				
			case "gerer":
				break;
		}
	}
	
	public void ajoutCommande() {
		
		Commande commande = new Commande(0, LocalDate.now(), null, null);
		daos.getCommandeDAO().create(commande);
	}
	
	
}

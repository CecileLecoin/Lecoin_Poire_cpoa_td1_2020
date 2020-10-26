/*package graphique;

import java.util.Scanner;

import dao.enumeration.Persistence;
import daofactory.DAOFactory;
import javafx.scene.input.MouseEvent;

public class Accueil {

    public Accueil() {

        System.out.println(
                "Bienvenue dans la gestion de votre boutique \n Voulez-vous travaillez sur la Base de données ou votre PC en local ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        choice = choice.toLowerCase();

        DAOFactory daos = null;

        if (choice == "bdd" || choice == "sql") {
            daos = DAOFactory.getDaoFactory(Persistence.MYSQL);
        }
        else if(choice == "listememoire" || choice == "lm") {
            daos = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE);
        }

        System.out.println(" Voulez vous gerer les produit, clients ou encore les categories ?");

        choice = sc.nextLine();
        choice = choice.toLowerCase();
        
        switch (choice) {
            case "produit": case "produits" : case "prod":
                new ManageProduits(daos);
                break;

            case "client": case "clients": case "les clients":
                new ManageClients(daos);
                break;

            case "categorie": case "categories": case "cat":
                new ManageCategories(daos);
                break;
            
            case "commande": case "commandes": case "com":
            	new ManageCommande(daos);
            	break;
        }
        sc.close();
    }

    //faut specifier UN SEUL controleur donc j'ai mis accueil (obv) du coup les managers appellent celui là... c'est pas opti
    public static void retourPage(MouseEvent mouseEvent)
    {
        new Accueil();
    }
}
*/
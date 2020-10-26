/*package graphique;

import java.util.Scanner;

import daofactory.DAOFactory;
import javafx.scene.input.MouseEvent;
import metier.Produit;

public class ManageProduits {

    private DAOFactory daos;

    public ManageProduits(DAOFactory daos) {

        this.daos = daos;

        System.out.println(
                "Que voulez vous faire avec les produit ? \n -Afficher la liste : 'liste'\n -Afficher un produit : 'produit'\n"
                + " -Ajouter un produit : 'ajout'\n - Supprimer un produit : 'suppr'\n -Modifier les informations d'un produit : 'modif'\n");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        choice = choice.toLowerCase();

        switch(choice) {
            case "afficher la liste" : case "liste" : case "la liste":
                for (Produit produit : daos.getProduitDAO().findAll()) {
                    System.out.println(produit);
                }
                break;

            case "afficher un produit": case "produit" : case "un produit":
                System.out.println("Rentrez le numero produit \n");

                System.out.println(daos.getProduitDAO().getById(sc.nextInt()));
                break;

            case "add": case "ajouter" : case "ajout":
                ajoutProduit();
                break;

            case "supprimer un produit": case "supprimer" : case "suppr":
                supprProduit();
                break;

            case "modifier un produit": case "modifier" : case "modif":
                modifProduit();
                break;
        }
        sc.close();
    }

    public void ajoutProduit() {
        System.out.println("Nouveau produit : \n");
        Scanner sc = new Scanner(System.in);

        System.out.println("Nom : \n");
        String nomProduit = sc.nextLine();
        System.out.println("description : \n");
        String descriptionProduit = sc.nextLine();
        System.out.println("tarif : \n");
        float tarifProduit = sc.nextFloat();
        sc.nextLine();
        System.out.println("visuel : \n");
        String visuelProduit = sc.nextLine();
        System.out.println("id caté : \n");
        int idCategorie = sc.nextInt();

        Produit produit = new Produit(0, nomProduit, descriptionProduit, visuelProduit, tarifProduit, daos.getCategorieDAO().getById(idCategorie));

        daos.getProduitDAO().create(produit);

        System.out.println("Fiche récap du produit " + produit);

        sc.close();
    }

    public void supprProduit() {
        System.out.println("ID du produit à supprimer : ");
        Scanner sc = new Scanner(System.in);

        int idProd = sc.nextInt();
        sc.nextLine();

        Produit produit = daos.getProduitDAO().getById(idProd);

        System.out.println(String.format("Supprimer le produit : %s  ? (o/n) %n", produit));
        if (sc.nextLine().equals("o")) {
            daos.getProduitDAO().delete(produit);
        }
        sc.close();
    }

    public void modifProduit() {
        System.out.println("Numero du produit a modifier : \n");
        Scanner sc = new Scanner(System.in);

        int idProd = sc.nextInt();
        sc.nextLine();

        Produit produit = daos.getProduitDAO().getById(idProd);
        System.out.println("Voulez vous modif ce produit  : %s ? (o/n) %n" + produit);

        if(sc.nextLine().equals("o")) {

            System.out.println("Nouveau nom : \n");
            produit.setNom(sc.nextLine());
            System.out.println("Nouvelle description : \n");
            produit.setDescription(sc.nextLine());
            System.out.println("Nouveau tarif : \n");
            produit.setTarif(sc.nextFloat());
            sc.nextLine();
            System.out.println("Nouveau visuel : \n");
            produit.setVisuel(sc.nextLine());

            daos.getProduitDAO().update(produit);

            System.out.println("Fiche récap du produit " + produit);
        }

        sc.close();
    }

    //FAIT PAR MOI DONC FAUT VERIFIER CE QUE JECRIS COMME POUR LES ENFANTS
    //fct retourPage : btn retour dans toutes les pages, doit faire revenir à la page précédente donc ici dans les managers de base on revient à l'acceuil
    public void retourPage(MouseEvent mouseEvent)
    {
        Accueil.retourPage(mouseEvent);
    }
}
*/
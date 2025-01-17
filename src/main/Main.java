package main;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.ProduitDAO;
import dao.enumeration.Persistence;
import dao.listememoire.ListeMemoireCategorieDAO;
import dao.listememoire.ListeMemoireClientDAO;
import dao.listememoire.ListeMemoireProduitDAO;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import graphique.control.PopUpSelectPersistence;
import graphique.controleur.ControlAccueil;
import graphique.controleur.ControlMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import metier.Categorie;
import metier.Client;
import metier.Commande;
import metier.Produit;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

public class Main extends Application {

	private static Main instance;

	public Stage primaryStage = new Stage();

	private Persistence persistence;
	private DAOFactory daos;

	@Override
	public void start(Stage primaryStage) {

		Main.instance = this;

		try {
			URL fxmlURL = getClass().getResource("/res/fxml/page/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

			Node root = fxmlLoader.load();
			Scene scene = new Scene((Parent) root);

			primaryStage.setScene(scene);
			scene.getStylesheets().add("/res/css/design1.css");
			primaryStage.setTitle("Bienvenue dans votre gestionnaire de boutique");
			primaryStage.setMinHeight(750);
			primaryStage.setMinWidth(1100);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		ControlMain.getInstance().push("/res/fxml/page/Accueil.fxml","Accueil");

		PopUpSelectPersistence popUpSelectPersistence = new PopUpSelectPersistence();
		Optional<Persistence> result = popUpSelectPersistence.showAndWait();

		if (result.isPresent() && result.get() != null) {
			this.persistence = result.get();
			this.daos = DAOFactory.getDaoFactory(persistence);
		}
		else {
			System.exit(1);
		}

		try {
			if (persistence == Persistence.LISTEMEMOIRE) {

				ControlAccueil.getInstance().setTogglePersistence(Persistence.LISTEMEMOIRE);

				ClientDAO clientdao = daos.getClientDAO();
				Client client = new Client(1, "nomLocal", "prenom", "identifiant", "mdp", "num", "voie", "cp", "ville", "pays");
				DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDate dateDebut = LocalDate.parse("2020-09-02 13:12:00", formatage);

				clientdao.create(client);

				ProduitDAO produitdao = daos.getProduitDAO();
				System.out.println("produitdao : " + produitdao.findAll().toString());
				CategorieDAO categoriedao = daos.getCategorieDAO();
				Categorie categorie = new Categorie(1, "titreCateCommande", "visuel");
				Produit p1 = new Produit(1, "nom", "description", "visuel", 4, categorie);

				daos.getCategorieDAO().create(categorie);
				daos.getProduitDAO().create(p1);

				HashMap<Produit, Integer> produits = new HashMap<>();
				produits.put(p1, 2);
				Commande c1 = new Commande(1, dateDebut, client, produits);
				System.out.println("commandes : " );
				dateDebut = LocalDate.parse("2020-08-30 11:22:00", formatage);
				produits.put(p1, 4);
				Commande c2 = new Commande(2, dateDebut, client, produits);
				daos.getCommandeDAO().create(c1);
				daos.getCommandeDAO().create(c2);
			}
			else {
				ControlAccueil.getInstance().setTogglePersistence(Persistence.MYSQL);
			}

		} catch (CommandeApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

	public static Main getInstance() { return instance; }

	public DAOFactory getDAO() { return daos; }

	public void setDAO(Persistence persistence) { this.daos = DAOFactory.getDaoFactory(persistence); this.persistence = persistence;}
	public Persistence getPersistence() { return this.persistence;}
}

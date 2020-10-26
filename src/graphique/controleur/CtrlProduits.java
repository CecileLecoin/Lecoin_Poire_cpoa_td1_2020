package graphique.controleur;

import dao.enumeration.Persistence;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import metier.Categorie;
import metier.Produit;

import java.net.URL;
import java.util.ResourceBundle;

public class CtrlProduits implements Initializable {

    @FXML
    private Label labelAffiche;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private ChoiceBox<Categorie> choiceBoxCategorie;
    @FXML
    private TextField textFieldTarif;
    @FXML
    private TextField textFieldNom;

    private DAOFactory dao;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE);
		try {
			dao.getCategorieDAO().create(new Categorie(0, "titre", "visuel"));
			this.choiceBoxCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
		} catch (CommandeApplicationException e) {
			e.printStackTrace();
		}
    }

    public void creerProduit() throws CommandeApplicationException {

    	Produit produit = new Produit();
    	Categorie categorie = this.choiceBoxCategorie.getValue();
    	String erreur = "";

    	try {
    		String nom = this.textFieldNom.getText().trim();
    		produit.setNom(nom);
    	} catch (IllegalArgumentException e){
    		erreur = e.getMessage();
    	}
    	try {
    		String description = this.textAreaDescription.getText().trim();
    		produit.setDescription(description);
    	} catch (IllegalArgumentException e){
    		erreur += e.getMessage();
    	}
    	try {
    		if (this.textFieldTarif.getText().trim().length() == 0){
    			erreur += "tarif non renseigné";
			}
    		else {
    		float tarif = Float.parseFloat(this.textFieldTarif.getText().trim());
			System.out.println(tarif);
    		produit.setTarif(tarif);
			}
    	} catch (IllegalArgumentException e){
    		erreur += e.getMessage();
    	}
    	if (categorie == null) {
    		erreur += "Aucune catégorie séléctionnée";
    	}
    	else {
    		produit.setCategorie(categorie);
    	}

		if (erreur.length() == 0) {
			this.labelAffiche.setText(produit.toString());
			this.labelAffiche.setStyle("-fx-text-fill: black;");

			dao.getProduitDAO().create(produit);
		}
		else {
			this.labelAffiche.setText(erreur);
			this.labelAffiche.setStyle("-fx-text-fill: red;");
		}

    }
}
